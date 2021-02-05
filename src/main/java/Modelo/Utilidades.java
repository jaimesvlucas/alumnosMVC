/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import Modelo.Alumno;

/**
 *
 * @author User
 */
public class Utilidades {
    public static ArrayList<Alumno> getProductos(String fichero) throws FileNotFoundException, IOException{
            ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
            Alumno a;
            try {

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), "utf-8"));
                    String alumno;
                    String alumnoLinea[];
                    try {

                            while((alumno = buffer.readLine())!=null) {
                                    alumnoLinea=alumno.split(";");
                                    a = new Alumno(Integer.parseInt(alumnoLinea[0]),alumnoLinea[1],
                                            alumnoLinea[2],alumnoLinea[3]);
                                    alumnos.add(a);					
                             }

                            buffer.close();
                    } catch (IOException e) {

                    }

            } catch (FileNotFoundException | UnsupportedEncodingException e) {

            }
            return alumnos;		
    }
}
