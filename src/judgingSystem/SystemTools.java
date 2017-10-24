package judgingSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

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
			@Override
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

		return res == "" ? (process.exitValue() == 0 ? null : "���볬ʱ") + "" : res;
	}

	/**
	 * ���� exe
	 * 
	 * @param std
	 *            ���Ե���Ϣ
	 * @param file
	 *            �ļ�Ŀ¼
	 * @param tempOutputFile
	 *            ��ʱ����ļ�Ŀ¼
	 * @return ������
	 */
	public static EvaluationResult runCPP(TestPoint std, String file, String tempOutputFile) {
		EvaluationResult ret = new EvaluationResult();

		try {

			String[] cmds = new String[] { "cmd.exe", "/c",
					"\"" + file + "\" < \"" + std.getTest_In() + "\" > \"" + tempOutputFile + "\" " };

			Process process = Runtime.getRuntime().exec(cmds);

			long begin = System.currentTimeMillis();

			new Thread() {
				@Override
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

					if (process.isAlive()) {
						ret.SetValue(EvaluationResult.Time_Exceeded);
						ret.setTimeConsum(System.currentTimeMillis() - begin);

						process.destroy();
					}
				}
			}.start();

			process.waitFor();

			ret.setTimeConsum(System.currentTimeMillis() - begin);

			if (ret.getValue() != EvaluationResult.Unknown_Error)
				return ret;

			if (process.exitValue() != 0) {
				ret.SetValue(EvaluationResult.Runtime_Error);

				return ret;
			}
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			ret.SetValue(EvaluationResult.System_Error);
			
			return ret;
		}
		
		return ret;

	}

	/**
	 * ��ͳ�𰸱Ƚ�
	 * 
	 * @param std
	 *            ���Ե���Ϣ
	 * @param tempOutputFile
	 *            ��ʱ����ļ�Ŀ¼
	 * @param result
	 *            ����ʱ�õ���������
	 * @return �𰸱ȽϽ��
	 */
	public static EvaluationResult resultComparison(TestPoint std, String tempOutputFile, EvaluationResult result) {
		if (result.getValue() != EvaluationResult.Unknown_Error)
			return result;

		try {
			BufferedReader Test_Read = new BufferedReader(new FileReader(tempOutputFile));
			BufferedReader STD_Read = new BufferedReader(new FileReader(std.getTest_Out()));
			String STD, player;

			while ((STD = STD_Read.readLine()) != null) {
				player = Test_Read.readLine();

				if (player != null) {
					if (!SystemTools.compareAns(STD, player, std.getJudgeMod())) {
						result.SetValue(EvaluationResult.Wrong_Answer);

						STD_Read.close();
						Test_Read.close();

						return result;
					}
				} else {
					if (STD.trim().equals(""))
						break;
					else {
						result.SetValue(EvaluationResult.Wrong_Answer);

						STD_Read.close();
						Test_Read.close();

						return result;
					}
				}
			}

			while ((STD = STD_Read.readLine()) != null)
				if (!STD.trim().equals("")) {
					result.SetValue(EvaluationResult.Wrong_Answer);

					STD_Read.close();
					Test_Read.close();

					return result;
				}

			while ((player = Test_Read.readLine()) != null)
				if (!player.trim().equals("")) {
					result.SetValue(EvaluationResult.Wrong_Answer);

					STD_Read.close();
					Test_Read.close();

					return result;
				}

			STD_Read.close();
			Test_Read.close();

			result.SetValue(EvaluationResult.Accepted);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			result.SetValue(EvaluationResult.System_Error);

			return result;
		}

		return result;
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
	private static boolean compareAns(String std, String player, int mod) {
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
	private static String trimeEndSpace(String str) {
		for (int i = str.length() - 1; i >= 0; i--)
			if (str.substring(i) == " ")
				str = str.substring(0, i);

		return str;
	}

}
