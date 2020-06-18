package extras;

import java.text.SimpleDateFormat;
import java.util.Date;

public class funcoes_uteis {
    public static void print(String text){
        System.out.println(text);
    }

    public static boolean dataValida(String data){
        String[] aux = data.split("/");

        if(aux.length != 3)
            return false;

        if(Integer.parseInt(aux[2]) < 2000)  //ano invalido para baixo
            return false;

        if(Integer.parseInt(aux[2]) > 2300)  //ano invalido para cima
            return false;

        if(Integer.parseInt(aux[1]) < 1)  //mes invalido para baixo
            return false;

        if(Integer.parseInt(aux[1]) > 12)  //mes invalido para cima
            return false;

        if(Integer.parseInt(aux[0]) < 1)  //dia invalido para baixo
            return false;

        switch (Integer.parseInt(aux[1])){  //para o meses excepto fevereiro ser invaldo para cima
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if(Integer.parseInt(aux[0]) > 31)
                    return false;
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                if(Integer.parseInt(aux[0]) > 30)
                    return false;
                break;
        }

        //ano bissexto??
        if((Integer.parseInt(aux[2]) % 400 == 0) || ((Integer.parseInt(aux[2]) % 4 == 0) && (Integer.parseInt(aux[2]) % 100 != 0))){
            if (Integer.parseInt(aux[1]) == 2)
                if (Integer.parseInt(aux[0]) > 29)
                    return false;
        }
        else{
            if (Integer.parseInt(aux[1]) == 2)
                if (Integer.parseInt(aux[0]) > 28)
                    return false;
        }

        return true;
    }

    public static Integer dataAnt_e_Pos(String dataAnt, String dataPos){
        String[] dataAnt_vars = dataAnt.split("/");
        String[] dataPos_vars = dataPos.split("/");

        if(!dataValida(dataAnt) || !dataValida(dataPos))
            return -1;

        if(Integer.parseInt(dataAnt_vars[2]) < Integer.parseInt(dataPos_vars[2]))
            return 1;

        if(Integer.parseInt(dataAnt_vars[2]) == Integer.parseInt(dataPos_vars[2]))
            if(Integer.parseInt(dataAnt_vars[1]) < Integer.parseInt(dataPos_vars[1]))
                return 1;
            else if(Integer.parseInt(dataAnt_vars[1]) == Integer.parseInt(dataPos_vars[1]))
                if(Integer.parseInt(dataAnt_vars[0]) <= Integer.parseInt(dataPos_vars[0]))
                    return 1;

        return 0;
    }

    public static String getActualDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
