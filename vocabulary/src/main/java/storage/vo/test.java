package storage.vo;

import java.util.Scanner;

public class test {
    public static final int[] list= {5973,7052,8669,15929};  
    public static double sumWater(int soLuong){
        double ans;
        if(soLuong < 0){
            return 0;
        }
        else if (soLuong <= 10){
            ans =  (soLuong*list[0])*1.15;
        }
        else if (soLuong <= 20){
            ans =  (10*list[0] + (soLuong-10)*list[1])*1.15;
        }
        else if (soLuong <= 30){
            ans =  (10*list[0] + (10)*list[1] + (soLuong-20)*list[2])*1.15;
        }
        else ans =  (10*list[0] + (10)*list[1] + (10)*list[2] + (soLuong-30)*list[3])*1.15;
        return Math.round(ans*100)/100.0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Math.round(sumWater(sc.nextInt())*100)/100.0);
    }

    public static double[] counter(int money, int k, int day){
        double ans = 0;
        double ans1=0;
        if(k==0){
            ans= (money*(0.1/100)*day)/365;
            ans1= (money*(0.1/100)*day)/365;
        }
        else if(k ==1){
            int t = day/30;
            int d = day - t*30;
            System.out.println(t + " " + d);
            ans= (money*(3/100)*t*30 + (money +money*(3/100)*t*30)*(0.1/100)*d)/365;
            ans1= (money*(0.1/100)*day)/365;
        }
        else if(k==2){
            int t = day/60;
            int d = day - t*60;
            ans= (money*(3.1/100)*t*60 + (money + money*(3.1/100)*t*60)*(0.1/100)*d)/365;
            ans1= (money*(0.1/100)*day)/365;
        }
        else if(k==3){
            int t = day/90;
            int d = day - t*90;
            ans= (money*(3.2/100)*t*90 + (money + money*(3.2/100)*t*90)*(0.1/100)*d)/365;
            ans1= (money*(3.3/100)*t*90 + (money + money*(3.3/100)*t*90)*(0.1/100)*d)/365;
        }
        else if(k==4){
            int t = day/120;
            int d = day - t*120;
            ans= (money*(3.2/100)*t*120 + (money + money*(3.2/100)*t*120)*(0.1/100)*d)/365;
            
            int t1 = day/90;
            int d1 = day - t*90;
            ans1= (money*(3.3/100)*t1*90 + (money + money*(3.3/100)*t1*90)*(0.1/100)*d1)/365;
        }
        else if(k==5){
            int t = day/150;
            int d = day - t*150;
            ans= (money*(3.2/100)*t*150 + (money + money*(3.2/100)*t*150)*(0.1/100)*d)/365;
            
            int t1 = day/90;
            int d1 = day - t*90;
            ans1= (money*(3.3/100)*t1*90 + (money + money*(3.3/100)*t1*90)*(0.1/100)*d1)/365;
        }
        else if(k==6){
            int t = day/180;
            int d = day - t*180;
            ans= (money*(4.8/100)*t*180 + (money + money*(4.8/100)*t*180)*(0.1/100)*d)/365;
            
            ans1= (money*(4.9/100)*t*180 + (money + money*(4.9/100)*t*180)*(0.1/100)*d)/365;

        }
        return new double[]{Math.round((ans*100))/100.0, Math.round((ans1*100))/100.0 };
    }
}
