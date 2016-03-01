package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner com = new Scanner(System.in);
        List<Bank> listBank = new ArrayList(); // ?????? ??????

        File file = new File("C:\\Temp\\sd.csv"); // ????? ? ?????????? ?????????
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "Windows-1251")) {
            BufferedReader in = new BufferedReader(reader); // ??? ??????
            String str; // ???? ??????
            String chars[]; // ???? ???????? ??????
            int i = 0;
            while ((str = in.readLine()) != null) { // ????????? ?????????
                if (i == 0) {
                    i++;
                    continue;
                } // ?????? ?????? ??????????
                Bank bank = new Bank(); // ??????? ????? ??????
                chars = str.split(";"); // ?????????? ??????
                bank.name = chars[0];
                bank.id_scet = Integer.parseInt(chars[1]);
                bank.budzet = Double.parseDouble(chars[2]);
                listBank.add(bank);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File pathToFile = new File("C:\\Temp");
        File listFiles[] = pathToFile.listFiles(); // ??? ????? ???????
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isFile() && !listFiles[i].getName().equals("sd.csv") && !listFiles[i].getName().equals("cd-final.csv")) {
                String ras[] = listFiles[i].getPath().split("\\."); //  ??????????? .
                if (ras[ras.length - 1].equals("csv")) {  // ???????  ?????????? ????????? ?????
                    // ????????? ????
                    try (InputStreamReader reader1 = new InputStreamReader(new FileInputStream(listFiles[i]), "Windows-1251")) {
                        BufferedReader in1 = new BufferedReader(reader1); // ??? ??????
                        String newLine; // ???? ??????
                        int flag = 0;
                        while ((newLine = in1.readLine()) != null) { // ????????? ?????????
                            if (flag == 0) {
                                flag++;
                                continue;
                            } // ?????? ?????? c ??????????? ???????????
                            String s[] = newLine.split(";"); // ?????????? ??????
                            // ???? ??????????? ? ??????????
                            int otprav = -1;
                            int polyc = -1;
                            for (int j = 0; j < listBank.size(); j++) {
                                if (otprav != -1 && polyc != -1) {
                                    break;
                                } // ???? ????? ??? ??????

                                if (otprav == -1) {  //  ???? ??????????? ???? ?? ?? ??????
                                    if (s[0].equals(listBank.get(j).name)) { // ?????????? ???
                                        if (Double.parseDouble(s[1]) == listBank.get(j).id_scet) { // ?????????? ?? ?????
                                            otprav = j;
                                        }
                                    }
                                }
                                if (polyc == -1) {  //  ???? ??????????? ???? ?? ?? ??????
                                    if (s[2].equals(listBank.get(j).name)) {
                                        if (Double.parseDouble(s[3]) == listBank.get(j).id_scet) {
                                            polyc = j;
                                        }
                                    }
                                }
                            }

                            // ???????? ????? ? ??????? ?????
                            if (otprav != -1 && polyc != -1) { // ???? ?????? ???????
                                double money = (Double.parseDouble(s[4]));
                                if (money <= listBank.get(otprav).budzet) {
                                    listBank.get(otprav).budzet -= money;
                                    listBank.get(polyc).budzet += money;
                                } else {
                                    //?????? ????? ?? ???????
                                    System.out.println("Transaction error from  "+ s[0]+ "  to  " + s[2]);
                                }
                            } else {
                                // ?????? ??? ??????????? ??? ` ?? ??????
                                System.out.println("Transaction error from  "+ s[0]+ "  to  " + s[2]);
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
       cout(listBank); // ????? ?????????? ? ????
    }

    public static void cout(List<Bank> listBank) { // ????? ???? ??????
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("C:\\Temp\\cd-final.csv"), "Windows-1251");) {
            out.append("???????? ????????;??????? ????;?????????????? ??????\r\n");
            for (int i = 0; i < listBank.size(); i++) {
                out.append(listBank.get(i).name);
                out.append(";");
                out.append(Integer.toString(listBank.get(i).id_scet));
                out.append(";");
                out.append(Double.toString(listBank.get(i).budzet));
                out.append("\r\n");
            }
            out.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
