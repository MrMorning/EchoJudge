package judgingSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;

/**
 * ����ϵͳ����
 * 
 * @author STR
 *
 */
public class SystemTools {

	/**
	 * Ԥ���ض�̬���ļ�
	 */
	public static void prestrain() {
		System.loadLibrary("SystemTools");
	}

	/**
	 * ����ڴ�
	 * 
	 * @param pid
	 *            ����ID
	 * @return �����ڴ�ռ���� ��λ KB
	 */
	public static native long getMemByPID(long pid);

	/**
	 * ����cpp�ļ������ر�����Ϣ
	 * 
	 * @param compile_File
	 *            G++������Ŀ¼
	 * @param fileName
	 *            �ļ�����
	 * @param outPutFile
	 *            ���Ŀ¼
	 * @return ������Ϣ
	 * @throws IOException
	 *             �������
	 * @throws InterruptedException
	 *             ϵͳ�쳣
	 */
	public static String compileCPP(String compile_File, String fileName, String outPutFile)
			throws IOException, InterruptedException {
		Process process = Runtime.getRuntime()
				.exec("\"" + compile_File + "\" \"" + fileName + "\" -o \"" + outPutFile + "\"");

		new Thread() {
			public void run() {
				try {
					for (int i = 1; i <= 10 && process.isAlive(); i++)
						Thread.sleep(1000);

					process.destroy();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		process.waitFor();

		InputStreamReader ir = new InputStreamReader(process.getErrorStream());
		LineNumberReader input = new LineNumberReader(ir);

		String line, res = "";

		while ((line = input.readLine()) != null)
			res += line + '\n';

		return res == "" ? (process.exitValue() == 0 ? "�ޱ�����Ϣ" : "���볬ʱ") + "" : res;
	}

	/**
	 * ɱ������
	 * 
	 * @param pid
	 *            ����ID
	 * @throws IOException
	 *             ��ֹ����ʱ�׳��쳣
	 */
	public static void killProcessByPid(long pid) throws IOException {
		Runtime.getRuntime().exec("taskkill /F /PID " + pid);
	}

	/**
	 * ���� exe
	 * 
	 * @param std
	 *            ���Ե���Ϣ
	 * @param file
	 *            �ļ�Ŀ¼
	 * @return ������
	 * @throws IOException
	 *             �����쳣
	 * @throws InterruptedException
	 *             ϵͳ�쳣
	 */
	public static EvaluationResult runCPP(TestPoint std, String file) throws IOException, InterruptedException {
		EvaluationResult ret = new EvaluationResult();

		BufferedReader read = new BufferedReader(new FileReader(std.getTest_In()));

		Process process = Runtime.getRuntime().exec(file);

		long begin = System.currentTimeMillis();
		OutputStream write = process.getOutputStream();
		String temp = null;

		while ((temp = read.readLine()) != null) {
			write.write((temp + "\n").getBytes());
			write.flush();
		}

		read.close();

		new Thread() {
			public void run() {
				long memory;

				while (System.currentTimeMillis() - begin <= std.getTime_Limit() && process.isAlive()) {
					try {
						memory = getMemByPID(process.pid());

						if (memory > ret.getMaxMemory())
							ret.setMaxMemory(memory);

						if (memory > std.getMemory_Limit() * 1024 * 1024) {
							process.destroy();

							ret.SetValue(EvaluationResult.Memory_Exceeded);
							ret.setTimeConsum(System.currentTimeMillis() - begin);

							return;
						}

						Thread.sleep(10);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

						ret.SetValue(EvaluationResult.System_Error);
						ret.setTimeConsum(System.currentTimeMillis() - begin);

						return;
					}
				}

				if (process.isAlive())
					process.destroy();
			}
		}.start();

		process.waitFor();

		if (ret.getValue() != EvaluationResult.Unknown_Error)
			return ret;

		if (System.currentTimeMillis() - begin > std.getTime_Limit()) {
			if (process.isAlive())
				process.destroy();

			ret.SetValue(EvaluationResult.Time_Exceeded);
			ret.setTimeConsum(System.currentTimeMillis() - begin);

			return ret;
		}

		if (process.exitValue() != 0) {
			ret.SetValue(EvaluationResult.Runtime_Error);
			ret.setTimeConsum(System.currentTimeMillis() - begin);

			return ret;
		}

		try {
			BufferedReader Test_Read = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader STD_Read = new BufferedReader(new FileReader(std.getTest_Out()));
			String STD, player;

			while ((STD = STD_Read.readLine()) != null) {
				player = Test_Read.readLine();

				if (player != null) {
					if (!SystemTools.compareAns(STD, player, std.getJudgeMod())) {
						ret.SetValue(EvaluationResult.Wrong_Answer);
						ret.setTimeConsum(System.currentTimeMillis() - begin);

						STD_Read.close();
						process.destroy();

						return ret;
					}
				} else {
					if (STD.trim().equals(""))
						break;
					else {
						ret.SetValue(EvaluationResult.Wrong_Answer);
						ret.setTimeConsum(System.currentTimeMillis() - begin);

						STD_Read.close();
						process.destroy();

						return ret;
					}
				}
			}

			while ((STD = STD_Read.readLine()) != null)
				if (!STD.trim().equals("")) {
					ret.SetValue(EvaluationResult.Wrong_Answer);
					ret.setTimeConsum(System.currentTimeMillis() - begin);

					STD_Read.close();
					process.destroy();

					return ret;
				}

			STD_Read.close();

			while ((player = Test_Read.readLine()) != null)
				if (!player.trim().equals("")) {
					ret.SetValue(EvaluationResult.Wrong_Answer);
					ret.setTimeConsum(System.currentTimeMillis() - begin);

					STD_Read.close();
					process.destroy();

					return ret;
				}

			ret.SetValue(EvaluationResult.Accepted);
			ret.setTimeConsum(System.currentTimeMillis() - begin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			ret.SetValue(EvaluationResult.System_Error);
			ret.setTimeConsum(System.currentTimeMillis() - begin);

			process.destroy();

			return ret;
		}

		return ret;
	}

	/**
	 * ���ո�����ʽ�Ա����
	 * 
	 * @param std
	 *            ��׼���
	 * @param player
	 *            ѡ�����
	 * @param mod
	 *            �ȽϷ�ʽ
	 * @return �ȽϽ��
	 */
	public static boolean compareAns(String std, String player, int mod) {
		switch (mod) {
		case TestPoint.Byte_By_Byte:
			return std.equals(player);
		case TestPoint.Ignore_Space:
			return SystemTools.trimeEndSpace(std).equals(SystemTools.trimeEndSpace(player));
		}

		return false;
	}

	/**
	 * �������ַ���ĩβ�ո�
	 * 
	 * @param str
	 *            ��Ҫ�������ַ���
	 * @return ���
	 */
	public static String trimeEndSpace(String str) {
		for (int i = str.length() - 1; i >= 0; i--)
			if (str.substring(i) == " ")
				str = str.substring(0, i);

		return str;
	}

}
