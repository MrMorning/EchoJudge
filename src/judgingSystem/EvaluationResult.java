package judgingSystem;

/**
 * ����Ϣ
 * 
 * @author lenovo
 *
 */
public class EvaluationResult {

	/**
	 * ͨ��
	 */
	public static final int Accepted = 1;
	/**
	 * ����������׼�𰸲�һ�£���������ĩ�ո��Լ��ļ�ĩ���У�
	 */
	public static final int Wrong_Answer = 2;
	/**
	 * ��������ʱ�䳬������Ŀ����
	 */
	public static final int Time_Exceeded = 3;
	/**
	 * ���������ڴ�ռ䳬������Ŀ����
	 */
	public static final int Memory_Exceeded = 4;
	/**
	 * ��������ʱ����
	 */
	public static final int Runtime_Error = 5;
	/**
	 * ϵͳ����
	 */
	public static final int System_Error = 6;
	/**
	 * ���ⱻȡ��
	 */
	public static final int Canceled = 7;
	/**
	 * δ֪����
	 */
	public static final int Unknown_Error = 8;

	private String SPJ_ret; // SPJ������ʾ
	private boolean SPJ;// �Ƿ�ʹ��SPJ
	private long SPJ_Score;// SPJ�÷�

	private int value;// ��״̬

	private long maxMemory;
	private long TimeConsum;

	/**
	 * ����һ������δ֪����ĳ�ʼ��
	 */
	public EvaluationResult() {
		value = EvaluationResult.Unknown_Error;
		SPJ = false;
	}

	/**
	 * ����һ�����и���ֵ�ĳ�ʼ��
	 * 
	 * @param value
	 *            ����������
	 */
	public EvaluationResult(int value) {
		this.value = value;
	}

	/**
	 * ����һ��ָ��SPJ�ĳ�ʼ��
	 * 
	 * @param value
	 *            �����������
	 * @param SPJ
	 *            �Ƿ�ʹ��SPJ
	 * @param SPJ_ret
	 *            SPJ��ʾ��Ϣ
	 * @param SPJ_Score
	 *            SPJ�÷�
	 */
	public EvaluationResult(int value, boolean SPJ, String SPJ_ret, long SPJ_Score) {
		this.value = value;
		this.SPJ_ret = SPJ_ret;
		this.SPJ = SPJ;
		this.SPJ_Score = SPJ_Score;
	}

	/**
	 * ���ó�����ʱ
	 * 
	 * @param TimeConsum
	 *            ������ʱ
	 */
	public void setTimeConsum(long TimeConsum) {
		this.TimeConsum = TimeConsum;
	}

	/**
	 * ��������ڴ�ռ��
	 * 
	 * @param memory
	 *            ����ڴ�ռ��
	 */
	public void setMaxMemory(long memory) {
		this.maxMemory = memory;
	}

	/**
	 * ��������������
	 * 
	 * @param value
	 *            ����������
	 */
	public void SetValue(int value) {
		this.value = value;
	}

	/**
	 * ����SPJ��Ϣ
	 * 
	 * @param SPJ
	 *            �Ƿ�ʹ��SPJ
	 * @param SPJ_ret
	 *            SPJ��ʾ��Ϣ
	 * @param SPJ_Score
	 *            SPJ�÷�
	 */
	public void setSPJ(boolean SPJ, String SPJ_ret, long SPJ_Score) {
		this.SPJ_ret = SPJ_ret;
		this.SPJ = SPJ;
		this.SPJ_Score = SPJ_Score;
	}

	/**
	 * ��ý������
	 * 
	 * @return �������
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * ���SPJʹ�����
	 * 
	 * @return �Ƿ�ʹ��SPJ
	 */
	public boolean getSPJ() {
		return this.SPJ;
	}

	/**
	 * ���SPJ��ʾ��Ϣ
	 * 
	 * @return SPJ��ʾ��Ϣ
	 */
	public String getSPJ_ret() {
		return this.SPJ_ret;
	}

	/**
	 * ���SPJ�÷�
	 * 
	 * @return SPJ�÷�
	 */
	public long getSPJ_Score() {
		return this.SPJ_Score;
	}

	/**
	 * ��ȡ����ռ������ڴ�
	 * 
	 * @return ����ռ������ڴ�
	 */
	public long getMaxMemory() {
		return this.maxMemory;
	}

	/**
	 * ��ȡ��������ʱ��
	 * 
	 * @return ��������ʱ��
	 */
	public long getTimeConsum() {
		return this.TimeConsum;
	}

}
