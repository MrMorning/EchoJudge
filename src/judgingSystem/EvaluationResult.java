package judgingSystem;

/**
 * ����Ϣ
 * 
 * @author lenovo
 *
 */
public class EvaluationResult {

	public static final int Accepted = 1; // ͨ��
	public static final int Wrong_Answer = 2; // ����������׼�𰸲�һ�£���������ĩ�ո��Լ��ļ�ĩ���У�
	public static final int Time_Exceeded = 3; // ��������ʱ�䳬������Ŀ����
	public static final int Memory_Exceeded = 4; // ���������ڴ�ռ䳬������Ŀ����
	public static final int Runtime_Error = 5; // ��������ʱ����
	public static final int Compile_Error = 6; // ����ʧ��
	public static final int System_Error = 7; // ϵͳ����
	public static final int Canceled = 8; // ���ⱻȡ��
	public static final int Unknown_Error = 9; // δ֪����

	private String SPJ_ret; // SPJ������ʾ
	private boolean SPJ;// �Ƿ�ʹ��SPJ
	private long SPJ_Score;// SPJ�÷�

	private int value;// ��״̬
	
	private long maxMemory;
	private long TimeConsum;

	public EvaluationResult() {
		value = EvaluationResult.Unknown_Error;
		SPJ = false;
	}

	public EvaluationResult(int value) {
		this.value = value;
	}

	public EvaluationResult(int value, boolean SPJ, String SPJ_ret, long SPJ_Score) {
		this.value = value;
		this.SPJ_ret = SPJ_ret;
		this.SPJ = SPJ;
		this.SPJ_Score = SPJ_Score;
	}

	public void setTimeConsum(long TimeConsum) {
		this.TimeConsum = TimeConsum;
	}
	
	public void setMaxMemory(long memory) {
		this.maxMemory = memory;
	}

	public void SetValue(int value) {
		this.value = value;
	}

	public void setSPJ(boolean SPJ, String SPJ_ret, long SPJ_Score) {
		this.SPJ_ret = SPJ_ret;
		this.SPJ = SPJ;
		this.SPJ_Score = SPJ_Score;
	}

	public int getValue() {
		return this.value;
	}

	public boolean getSPJ() {
		return this.SPJ;
	}

	public String getSPJ_ret() {
		return this.SPJ_ret;
	}

	public long getSPJ_Score() {
		return this.SPJ_Score;
	}

	public long getMaxMemory() {
		return this.maxMemory;
	}
	
	public long getTimeConsum() {
		return this.TimeConsum;
	}

}
