package th4.test;

import java.text.DecimalFormat;

import th4.hihi.Phong;

public class NguoiDung extends Thread {
	private TuiTien tuiTien;
	private String sinhVien;
	DecimalFormat decimalFormat = new DecimalFormat("###,###");
	public String tenhang;
	public int tien;

	public NguoiDung(String sinhVien, TuiTien a, String tenhang, int tien) {
		super();
		this.sinhVien = sinhVien;
		this.tuiTien = a;
		this.tenhang = tenhang;
		this.tien = tien;
	}

	public NguoiDung(String sinhVien) {
		super();
		this.sinhVien = sinhVien;

	}

	public void sudung(TuiTien p) {
		this.tuiTien = p;
	}

	@Override
	public void run() {

		if (!tuiTien.isUsing()) {
			System.out.println(sinhVien + " waiting");		
		}
		synchronized (tuiTien) {
				
			// dang mua hang
			while (!tuiTien.isUsing()) {
				try {
					tuiTien.wait();	//cho tui tien khong ai su dung
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("error : "+e);
					e.printStackTrace();
				}
				
				
			}
			tuiTien.setUsing(false);
			boolean abc = true;
			System.out.println(sinhVien + " dang mua " + tenhang + " gia " + decimalFormat.format(tien) + " vnd");
			if (tuiTien.getTien() > tien) {
				tuiTien.setTien(tuiTien.getTien() - tien);
				abc = true;

			} else if (tuiTien.getTien() < tien) {
				abc = false;

			}
			try {
				Thread.sleep(3000);			// mua hang trong 3s
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (abc == true) {
				System.out.println(sinhVien + " da mua " + tenhang + " thanh cong");

			}
			if (abc == false) {
				System.out.println("khong du tien mua " + tenhang);

			}
		
			System.out.println("so tien con lai : " + decimalFormat.format(tuiTien.getTien()) + " vnd" + "\n");

			try {
				Thread.sleep(2000);			// tra tui tien ve lai cho cu~
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tuiTien.setUsing(true);
			tuiTien.notify();		// tra ve thanh cong -  cap nhat lai du lieu 

		}

	}

	public static void main(String[] args) throws InterruptedException {

		TuiTien tuiTien = new TuiTien(100000, true); // 100 000 ngan
		NguoiDung phuc = new NguoiDung("phuc", tuiTien, "keo", 50000);
		NguoiDung nam = new NguoiDung("nam", tuiTien, "banh", 10000);
		NguoiDung hiep = new NguoiDung("hiep", tuiTien, "gao", 10000);
		phuc.sudung(tuiTien);
		nam.sudung(tuiTien);
		hiep.sudung(tuiTien);
		phuc.start();
		nam.start();
		hiep.start();

	}

}
