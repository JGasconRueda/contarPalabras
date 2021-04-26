package com.example.contarpalabras;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        String arrayPalabras =
                "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un " +
                        "hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. " +
                        "Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos " +
                        "los sábados, lantejas los viernes, algún palomino de añadidura los domingos, consumían " +
                        "las tres partes de su hacienda. El resto della concluían sayo de velarte, calzas de " +
                        "velludo para las fiestas, con sus pantuflos de lo mesmo, y los días de entresemana se " +
                        "honraba con su vellorí de lo más fino. Tenía en su casa una ama que pasaba de los " +
                        "cuarenta y una sobrina que no llegaba a los veinte, y un mozo de campo y plaza que " +
                        "así ensillaba el rocín como tomaba la podadera. Frisaba la edad de nuestro hidalgo " +
                        "con los cincuenta años. Era de complexión recia, seco de carnes, enjuto de rostro, " +
                        "gran madrugador y amigo de la caza. Quieren decir que tenía el sobrenombre de Quijada, " +
                        "o Quesada, que en esto hay alguna diferencia en los autores que deste caso escriben, " +
                        "aunque por conjeturas verisímilesII se deja entender que se llamaba Quijana. Pero " +
                        "esto importa poco a nuestro cuento: basta que en la narración dél no se salga un " +
                        "punto de la verdad.";



        Map<String,Integer> diccionarioFor = procesaConFor(arrayPalabras);
        System.out.println("----------- DiccionarioFor----------------");
        imprimir(diccionarioFor);

        Map<String,Integer> diccionarioStream = procesaConStreams(arrayPalabras);
        System.out.println();
        System.out.println();
        System.out.println("----------- DiccionarioStream----------------");
        imprimir(diccionarioStream);

        System.out.println();
        System.out.println();
        System.out.println("----------- Iguales----------------");
        System.out.println("Iguales = "+sonIguales(diccionarioFor,diccionarioStream));
    }

    private static Map<String,Integer> procesaConFor(String arrayPalabras){

        Map<String,Integer> result = new HashMap<>();

        String[] array = arrayPalabras.split(" ");

        for (String s : array) {
            s = s.replaceAll("[,.]", "");
            Integer valor = result.get(s);
            if (valor == null) {
                result.put(s, 1);
            } else {
                valor++;
                result.replace(s, valor);
            }
        }

        return result;
    }

    private static Map<String,Integer> procesaConStreams(String arrayPalabras){

        return Arrays.stream(arrayPalabras.split(" ")).collect(
                Collectors.toMap(s1 -> s1.replaceAll("[,.]", ""), s1 ->1,(s1, s2) -> s1=s1+1));
    }

    private static boolean sonIguales(Map<String, Integer> first, Map<String, Integer> second) {
        if (first.size() != second.size()) {
            return false;
        }

        return first.entrySet()
                .stream()
                .allMatch(e -> e.getValue()
                        .equals(second.get(e.getKey())));
    }

    private static void imprimir(Map<String, Integer> map){
        map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.print(entry.getKey()+": "+entry.getValue()+" | "));
    }
}
