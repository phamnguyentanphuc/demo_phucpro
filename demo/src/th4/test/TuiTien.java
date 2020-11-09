package th4.test;



public class TuiTien {
	private int Tien;
	private boolean using;
	public TuiTien(int tien) {
		Tien = tien;	
	}
	public TuiTien(Boolean b) {
		using = b;	
	}
	public TuiTien(int tien,boolean b) {
		Tien = tien;	
		using=b;
	}
	public int getTien() {
		return Tien;
	}
	public void setTien(int tien) {
		Tien = tien;
	}
	public boolean isUsing() {
		return using;
	}
	public void setUsing(boolean using) {
		this.using = using;
	}


	

}
