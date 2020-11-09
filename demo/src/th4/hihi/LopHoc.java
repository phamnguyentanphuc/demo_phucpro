package th4.hihi;


	public class LopHoc extends Thread {
		private Phong phong;
		private String tenLop;

		public LopHoc(String ten) {
			this.tenLop = ten;
		}

		public void dangKyPhong(Phong p) {
			this.phong = p;
		}

		@Override
		public void run() {
			
			if (!phong.isTrong())
				System.out.println(this.tenLop + " cho phong");
			synchronized (this.phong) {
				while (!phong.isTrong()) {
					try {
						phong.wait();		// chờ có phòng trống
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// giải sử mỗi lớp sử dụng phòng trong 5 giấy:
				this.phong.setTrong(false);
				System.out.println(this.tenLop + " dang su dung phong");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// trả phòng sau khi hết 5 giây:
				System.out.println(this.tenLop + " tra phong");
				this.phong.setTrong(true);
				phong.notify();//báo cho các lớp đang chờ phòng biết!
			}
		}

		public static void main(String[] args) throws InterruptedException {
			Phong p = new Phong(true);
			LopHoc l1 = new LopHoc("Lớp A");
			LopHoc l2 = new LopHoc("Lớp B");
			LopHoc l3 = new LopHoc("Lớp C");
			l1.dangKyPhong(p);
			l2.dangKyPhong(p);
			l3.dangKyPhong(p);
			l1.start();
			l2.start();
			l3.start();
		}
	}
