package judgingSystem;

/**
 * ���Ե���Ϣ
 * 
 * @author STR
 *
 */
public class TestPoint {

	/**
	 * ���ֱȽϷ�ʽ
	 */
	public static final int Byte_By_Byte = 1;
	/**
	 * ���Կ����Լ���ĩ�ո�ȽϷ�ʽ
	 */
	public static final int Ignore_Space = 2;

	private long Time_Limit; // ʱ������
	private long Memory_Limit; // �ڴ�����
	private long Score; // ����

	private String Test_In;// ���Ե������ļ�·��
	private String Test_Out;// ���Ե�����ļ�·��

	private int JudgeMod;

	private boolean SPJ;// �Ƿ���SPJ
	private String SPj_File; // SPJ·��

	/**
	 * ͨ�������𰸱ȽϷ�ʽ������Ե����
	 * 
	 * @param JudgeMod
	 *            �𰸱Ͻڷ�ʽ
	 */
	public TestPoint(int JudgeMod) {
		this.JudgeMod = JudgeMod;
		Time_Limit = 0;
		Memory_Limit = 0;
		Score = 0;
		SPJ = false;
	}

	/**
	 * ͨ��ʱ�����ƣ��ڴ����ƣ����Ե��ֵ���𰸱ȽϷ�ʽ�������ļ�λ�ã�����ļ�λ�ù�����Ե����
	 * 
	 * @param Time_Limit
	 *            ʱ������
	 * @param Memory_Limit
	 *            �ڴ�����
	 * @param Score
	 *            ���Ե��ֵ
	 * @param JudgeMod
	 *            �𰸱ȽϷ�ʽ
	 * @param Test_In
	 *            �����ļ�λ��
	 * @param Test_Out
	 *            ����ļ�λ��
	 */
	public TestPoint(long Time_Limit, long Memory_Limit, long Score, int JudgeMod, String Test_In, String Test_Out) {
		this.JudgeMod = JudgeMod;
		this.Time_Limit = Time_Limit;
		this.Memory_Limit = Memory_Limit;
		this.Score = Score;
		this.Test_In = Test_In;
		this.Test_Out = Test_Out;
		this.SPJ = false;
	}

	/**
	 * ͨ��ʱ�����ƣ��ڴ����ƣ����Ե��ֵ���𰸱ȽϷ�ʽ�������ļ�λ�ã�����ļ�λ�ã�SPJʹ�������SPJ�ļ�Ŀ¼������Ե����
	 * 
	 * @param Time_Limit
	 *            ʱ������
	 * @param Memory_Limit
	 *            �ڴ�����
	 * @param Score
	 *            ���Ե��ֵ
	 * @param JudgeMod
	 *            �𰸱ȽϷ�ʽ
	 * @param Test_In
	 *            �����ļ�λ��
	 * @param SPJ
	 *            SPJʹ�����
	 * @param SPJ_File
	 *            SPJ�ļ�Ŀ¼
	 */
	public TestPoint(long Time_Limit, long Memory_Limit, long Score, int JudgeMod, String Test_In, boolean SPJ,
			String SPJ_File) {
		this.JudgeMod = JudgeMod;
		this.Time_Limit = Time_Limit;
		this.Memory_Limit = Memory_Limit;
		this.Score = Score;
		this.Test_In = Test_In;
		this.SPJ = SPJ;
		this.SPj_File = SPJ_File;
	}

	/**
	 * ���ô𰸱ȽϷ�ʽ
	 * 
	 * @param JudgeMod
	 *            �𰸱ȽϷ�ʽ
	 */
	public void SetJudgeMod(int JudgeMod) {
		this.JudgeMod = JudgeMod;
	}

	/**
	 * ��������ļ�Ŀ¼
	 * 
	 * @param Test_Out
	 *            ����ļ�Ŀ¼
	 */
	public void setTest_Out(String Test_Out) {
		this.Test_Out = Test_Out;
	}

	/**
	 * ���������ļ�Ŀ¼
	 * 
	 * @param Test_In
	 *            �����ļ�Ŀ¼
	 */
	public void setTest_In(String Test_In) {
		this.Test_In = Test_In;
	}

	/**
	 * ����SPJ�ļ�Ŀ¼
	 * 
	 * @param SPJ_File
	 *            SPJ�ļ�Ŀ¼
	 */
	public void setSPJFIle(String SPJ_File) {
		this.SPj_File = SPJ_File;
	}

	/**
	 * ����SPJʹ�����
	 * 
	 * @param SPJ
	 *            SPJʹ�����
	 */
	public void setSPJ(boolean SPJ) {
		this.SPJ = SPJ;
	}

	/**
	 * ����ʱ������
	 * 
	 * @param time
	 *            ʱ������
	 */
	public void setTime_Limit(long time) {
		this.Time_Limit = time;
	}

	/**
	 * �����ڴ�����
	 * 
	 * @param memory
	 *            �ڴ�����
	 */
	public void setMemory_Limit(long memory) {
		this.Memory_Limit = memory;
	}

	/**
	 * ���ò��Ե��ֵ
	 * 
	 * @param Score
	 *            ���Ե��ֵ
	 */
	public void setScore(long Score) {
		this.Score = Score;
	}

	/**
	 * ��ȡ���Ե�ʱ������
	 * 
	 * @return ���Ե�ʱ������
	 */
	public long getTime_Limit() {
		return this.Time_Limit;
	}

	/**
	 * ��ȡ���Ե��ڴ�����
	 * 
	 * @return ���Ե��ڴ�����
	 */
	public long getMemory_Limit() {
		return this.Memory_Limit;
	}

	/**
	 * ��ȡ���Ե��ֵ
	 * 
	 * @return ���Ե��ֵ
	 */
	public long getScore() {
		return this.Score;
	}

	/**
	 * ��ȡSPJʹ�����
	 * 
	 * @return �Ƿ�ʹ��SPJ
	 */
	public boolean getSPJ() {
		return this.SPJ;
	}

	/**
	 * ��ȡSPJ�ļ�Ŀ¼
	 * 
	 * @return SPJ�ļ�Ŀ¼
	 */
	public String getSPJFile() {
		return this.SPj_File;
	}

	/**
	 * ��ȡ�����ļ�Ŀ¼
	 * 
	 * @return �����ļ�Ŀ¼
	 */
	public String getTest_In() {
		return this.Test_In;
	}

	/**
	 * ��ȡ����ļ�Ŀ¼
	 * 
	 * @return ����ļ�Ŀ¼
	 */
	public String getTest_Out() {
		return this.Test_Out;
	}

	/**
	 * ��ȡ�𰸱ȽϷ�ʽ
	 * 
	 * @return �𰸱ȽϷ�ʽ
	 */
	public int getJudgeMod() {
		return this.JudgeMod;
	}

}
