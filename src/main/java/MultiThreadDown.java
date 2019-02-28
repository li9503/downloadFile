import java.util.Date;

/**
 * @Author 李景磊
 * @Description
 * @Date 2019/2/7 10:01
 * @ModifiedBy：
 */
public class MultiThreadDown {
   static   int start=100;
   static int stop=964;
    static int i=stop-start;
//    static int[] k={1108};
//    static int i=k.length;

    public static void main(String[] args) throws Exception {
//        for(int i:k){
//            down(i);
//        }
        for(int i=start;i<stop;i++){
//            down(i);
            System.out.println("http://180h.ysts8.com:8000/%E7%8E%84%E5%B9%BB%E5%B0%8F%E8%AF%B4/%E5%BC%A0%E4%B8%89%E4%B8%B0%E5%BC%82%E7%95%8C%E6%B8%B8/"+i+".mp3?1403352583473x1550843246x1403358714133-97c68db14ed0688441efa72406938903?3");
        }
    }
    public static String change(int num) {
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //余数
        int i=(num-1)%26;
        int j=(num-i)/26;
        int k=j/26;

        StringBuffer s=new StringBuffer();
        s.append(string.charAt(i));
        if (j>0){
            s.append(string.charAt(j));
            if (k>0){
                s.append(string.charAt(k));
            }
        }else if (k>0){
            s.append(string.charAt(k));
        }
        return s.reverse().toString();
    }
    public static void down(int fileName) throws Exception {
        final DownUtil downUtil = new DownUtil("http://180h.ysts8.com:8000/%E7%8E%84%E5%B9%BB%E5%B0%8F%E8%AF%B4/%E5%BC%A0%E4%B8%89%E4%B8%B0%E5%BC%82%E7%95%8C%E6%B8%B8/"+fileName+".mp3?1403352582491x1550842308x1403358713151-0e5e63912ea4cd4a5692a587800378cf?3","F:\\近身兵王\\"+fileName+".mp3",1);
        downUtil.downLoad();
long time=System.currentTimeMillis();
        new Thread(() -> {
            while(downUtil.getCompleteRate() < 1)
            {
//                System.out.println(fileName+"已完成: "+ downUtil.getCompleteRate());
                try{//每隔0.1秒查询一次任务
                    Thread.sleep(1000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            i=i-1;
            System.out.print(fileName+"已完成: "+ downUtil.getCompleteRate()+"还剩"+i+"个"+new Date());
            System.out.println("平均速度："+downUtil.fileSize/(System.currentTimeMillis()-time)+"kb/s");
        }).start();
    }
}
