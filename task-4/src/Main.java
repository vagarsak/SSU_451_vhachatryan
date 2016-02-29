import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner com = new Scanner(System.in);
        List<bank> zik = new ArrayList(); // массив банков

        File f=new File("E:\\sd.csv"); // банки с начальными бюджетами
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(f),"Windows-1251"))
        {
            BufferedReader in = new BufferedReader(reader); // для чтения
            String ss; // сюда строку
            String s[]; // сюда элементы строки
            int i = 0;
            while( (ss = in.readLine() )!= null) { // считываем построчно
                if(i == 0){i++; continue;} // первую строку пропускаем
                bank kit = new bank(); // создаем новый обьект
                s = ss.split(";"); // возвращяем массив
                kit.name = s[0];
                kit.id_scet = Integer.parseInt(s[1]);
                kit.budzet = Double.parseDouble(s[2]);
                zik.add(kit);
            }

        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }


        File f1=new File("E:\\");
        File ziki[] = f1.listFiles(); // все файлы диалога
        for(int i = 0; i<ziki.length; i++) {
            if(ziki[i].isFile() && !ziki[i].getName().equals("sd.csv") && !ziki[i].getName().equals("cd-final.csv")) {
                String ras[] = ziki[i].getPath().split("\\."); //  разделитель .
                if(ras[ras.length-1].equals("csv")){  // выводим  расширение последняя точка
                    // открываем файл
                    try(InputStreamReader reader1 = new InputStreamReader(new FileInputStream(ziki[i]),"Windows-1251"))
                    {
                        BufferedReader in1 = new BufferedReader(reader1); // для чтения
                        String ss1; // сюда строку
                        int flag = 0;
                        while( (ss1 = in1.readLine() )!= null) { // считываем построчно
                            if(flag == 0){flag++; continue;} // первую строку c информацией ппропускаем
                            String s[] =  ss1.split(";"); // компоненты строки
                            // ищем отправителя и получателя
                            int otprav =  -1;
                            int polyc = -1;
                            for(int j = 0; j<zik.size(); j++){
                                if(otprav != -1 && polyc != -1){break;} // если нашли все данные

                                if(otprav == -1){  //  ищем отправителя если он не найден
                                    if(s[0].equals(zik.get(j).name)){ // сравниваем имя
                                        if(Double.parseDouble(s[1]) ==  zik.get(j).id_scet) { // сравниваем ид счета
                                            otprav = j;
                                        }
                                    }
                                }
                                if(polyc == -1){  //  ищем отправителя если он не найден
                                    if(s[2].equals(zik.get(j).name)){
                                        if(Double.parseDouble(s[3]) == zik.get(j).id_scet) {
                                            polyc = j;
                                        }
                                    }
                                }
                            }

                            // проверка счета и перевод денег
                            if(otprav != -1 && polyc != -1) { // если данные найдены
                                double dengi = (Double.parseDouble(s[4]));
                                if(dengi <= zik.get(otprav).budzet){
                                    zik.get(otprav).budzet -= dengi;
                                    zik.get(polyc).budzet +=dengi;
                                }else {
                                    //ошибка денег не хватает
                                    System.out.println(ss1);
                                }
                            }else {
                                // ошибка или отправитель или получатель не найден
                                System.out.println(ss1);
                            }
                        }

                    }
                    catch(IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

        kit(zik); // вывод результата в файл

    }

  public static void kit (List<bank> zik) { // вывод всех банков
      try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("E:\\cd-final.csv"),"Windows-1251");)
      {
          out.append("Название компании;Лицевой счет;Первоначальный бюджет\r\n");
          for(int i =0; i<zik.size(); i++){
              out.append(zik.get(i).name);
              out.append(";");
              out.append(Integer.toString(zik.get(i).id_scet));
              out.append(";");
              out.append(Double.toString(zik.get(i).budzet));
              out.append("\r\n");
          }
          out.flush();
      }
      catch(IOException ex) {
          System.out.println(ex.getMessage());
      }
  }

}
