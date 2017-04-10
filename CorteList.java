package com.example.aluno.myapplication;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Aluno on 23/02/2017.
 */
public class CorteList {

    List<Corte> cortes = new LinkedList<Corte>();
    Connection connection = new Connection();


    public void getCortes(){
        try {
            cortes = connection.sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> returnCortes(){
        List<String> corte = new LinkedList<String>();
        for(Corte cort :cortes){
            // corte.add(cort.getCorte()+"&"+cort.getUrls());
            corte.add(cort.getCorte());
        }
        return corte;
    }
    public List<String> returnURL(){
        List<String> links = new LinkedList<String>();
        for(Corte cort :cortes){
            links.add(cort.getUrls());
        }
        return links;
    }

    public String returnU(String nome) {
        for (Corte cort : cortes) {
            if (cort.getCorte().equals(nome)) {
                return cort.getUrls();
            }
        }
        return "Not found link";
    }

    public String returnGRANA(String nome) {
        for (Corte cort : cortes) {
            if (cort.getCorte().equals(nome)) {
                return cort.getGrana();
            }
        }
        return "Not found Grana";
    }

    public String returnLocation(String nome) {
        for (Corte cort : cortes) {
            if (cort.getCorte().equals(nome)) {
                return cort.getLocation();
            }
        }
        return "Not found location";
    }



}