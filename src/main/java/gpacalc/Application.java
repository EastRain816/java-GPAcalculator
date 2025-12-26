package gpacalc;
import java.util.*;

public class Application {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String input = "";
        String[] majorInput;
        String[] generalInput;
        int credit = 0;
        double gpaAvg = 0.0;
        double majorGpaAvg = 0.0;
        double gpaOfSubject = 0.0;
        int creditOfSubject = 0;
        double weight = 0.0;

        System.out.println("전공 과목명과 이수학점, 평점을 입력해주세요(예시: 프로그래밍언어론-3-A+,소프트웨어공학-3-B+):");
        input = s.next();
        majorInput = input.split(",");
        validation(majorInput);

        System.out.println("\n교양 과목명과 이수학점, 평점을 입력해주세요(예시: 선형대수학-3-C0,인간관계와자기성장-3-P):");
        input = s.next();
        generalInput = input.split(",");
        validation(generalInput);

        System.out.println("\n<과목 목록>");
        for (String string : majorInput) {
            System.out.print("[전공] ");
            creditOfSubject = calculateCredit(string);
            gpaOfSubject = calculateGpa(string);

            if(gpaOfSubject != 5.0 && gpaOfSubject != -1.0) {
                gpaAvg += gpaOfSubject * creditOfSubject; // P, NP는 평점에서 제외
                weight += creditOfSubject; // 가중치에서도 제외
            }

            if(gpaOfSubject != 0.0 && gpaOfSubject != -1.0) credit += creditOfSubject; // F, NP는 취득학점 제외
        }
        majorGpaAvg = gpaAvg / weight;

        for (String string : generalInput) {
            System.out.print("[교양] ");
            creditOfSubject = calculateCredit(string);
            gpaOfSubject = calculateGpa(string);

            if(gpaOfSubject != 5.0 && gpaOfSubject != -1.0) {
                gpaAvg += gpaOfSubject * creditOfSubject; // P, NP는 평점에서 제외
                weight += creditOfSubject; // 가중치에서도 제외
            }

            if(gpaOfSubject != 0.0 && gpaOfSubject != -1.0) credit += creditOfSubject; // F, NP는 취득학점 제외
        }
        gpaAvg = gpaAvg / weight;

        System.out.println("\n<취득학점>");
        System.out.println(credit + "학점");
        System.out.println("\n<평점평균>");
        System.out.println(String.format("%.2f", gpaAvg) + " / 4.5");
        System.out.println("\n<전공 평점평균>");
        System.out.println(String.format("%.2f", majorGpaAvg) + " / 4.5");
    }

    public static double calculateGpa(String subjectInput){

        String[] subjectInfo = subjectInput.split("-");

        for(int i = 0; i < subjectInfo.length; i++){
            System.out.print(subjectInfo[i]);
            if(i != 2) System.out.print(",");
            else System.out.println();
        }

        double gpa = 0.0;

        if(subjectInfo[2].equals("A+")){
            gpa = 4.5;
        } else if (subjectInfo[2].equals("A0")) {
            gpa = 4.0;
        } else if (subjectInfo[2].equals("B+")) {
            gpa = 3.5;
        } else if (subjectInfo[2].equals("B0")) {
            gpa = 3.0;
        } else if (subjectInfo[2].equals("C+")) {
            gpa = 2.5;
        } else if (subjectInfo[2].equals("C0")) {
            gpa = 2.0;
        }else if (subjectInfo[2].equals("D+")) {
            gpa = 1.5;
        } else if (subjectInfo[2].equals("D0")) {
            gpa = 1.0;
        } else if (subjectInfo[2].equals("P")) {
            gpa = 5.0;
        } else if (subjectInfo[2].equals("NP")) {
            gpa = -1.0;
        }
        return gpa;
    }

    public static int calculateCredit(String subjectInput){
        String[] subjectInfo = subjectInput.split("-");
        return Integer.parseInt(subjectInfo[1]);
    }

    public static void validation(String[] subjectsInput){
        String[] subjectInfo;
        for(int i = 0; i < subjectsInput.length; i++){
            subjectInfo = subjectsInput[i].split("-");
            if(subjectInfo[0].length() > 10 || subjectInfo[0].isBlank()) throw new IllegalArgumentException("잘못된 과목명 입력");
            if(Integer.parseInt(subjectInfo[1]) < 1 || Integer.parseInt(subjectInfo[1]) > 4) throw new IllegalArgumentException("잘못된 학점 입력");
            switch (subjectInfo[2]) {
                case "A+": break;
                case "A0": break;
                case "B+": break;
                case "B0": break;
                case "C+": break;
                case "C0": break;
                case "D+": break;
                case "D0": break;
                case "F": break;
                case "P": break;
                case "NP": break;
                default: throw new IllegalArgumentException("잘못된 성적 입력");
            }
        }
    }
}