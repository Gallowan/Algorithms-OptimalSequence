/**
 * CS 331.02
 * Professor: Salloum
 *
 * Programming Assignment #4
 *
 * Justin Galloway
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        ArrayList<LinkedList> aList = new ArrayList<LinkedList>();

        boolean checkContinue = true;
        while(checkContinue){
            LinkedList<Node> lList = new LinkedList<Node>();

            // Check to continue filling
            boolean finish=true;
            while(finish){
                Node node = new Node();
                finish = testCheck(node);
                if(finish) {
                    lList.add(node);
                    printOut(lList);
                }
            }

            if(lList.size()>0) aList.add(lList);
            printOut(aList);
            checkContinue = exitCheck();
        }

        ratioCalc(aList);
        LinkedList optimal = getOptimalSeq(aList);
        System.out.println("Optimal Sequence:\n");
        printOut(optimal);
    }

    // Node for entering the tests
    public static class Node{
        String name;
        int cost;
        float prob;
        int totalcost;
        float totalprob;
        float ratio;
        Node(){
            name = "";
            cost = 0;
            prob = 0;
            totalcost=0;
            totalprob=0.0f;
            ratio=0.0f;
        }

        Node(String n, int c, float p){
            name = n;
            cost = c;
            prob = p;
            totalcost=0;
            totalprob=0.0f;
            ratio=0.0f;
        }
    }

    // Calculation of the ratio provided in the instructions
    private static void ratioCalc(ArrayList<LinkedList> aList) {
        for(int i=0 ; i < aList.size(); i++){
            LinkedList lList = (LinkedList)aList.get(i);

            for(int j=0; j < lList.size(); j++){
                Node node = (Node)lList.get(j);

                if(j==0){
                    node.totalcost=node.cost;
                    node.totalprob=node.prob;
                }else{
                    Node before = (Node)lList.get(j-1);
                    node.totalcost= node.cost + before.totalcost;
                    node.totalprob = node.prob + before.totalprob;
                }

                node.ratio=node.totalcost/node.totalprob;
            }
        }
    }

    // Find the optimal sequence (op) in a string and return it
    private static LinkedList getOptimalSeq(ArrayList<LinkedList> a) {
        int maxLength = getMaxLength(a);
        LinkedList OP = new LinkedList<Node>();
        float lowestRatio = 999999.0f;
        int minIValue = 0;
        int minJValue = 0;
        LinkedList lList;

        while(maxLength>0){
            lowestRatio=999999.0f;
            for(int i=0; i<a.size();i++){
                lList = (LinkedList)a.get(i);
                for(int j=0; j<lList.size();j++){
                    Node n = (Node)lList.get(j);
                    if(lowestRatio>n.ratio){
                        lowestRatio=n.ratio;
                        minIValue=i;
                        minJValue=j;
                    }
                }
            }

            lList = (LinkedList)a.get(minIValue);
            LinkedList temp = new LinkedList<Node>();
            for(int i=0; i<=minJValue; i++){
                temp.add(lList.get(i));
            }

            OP.addAll(temp);
            temp.clear();
            for(int i=minJValue+1; i<lList.size();i++){
                temp.add(lList.get(i));
            }

            a.set(minIValue, temp);
            maxLength = getMaxLength(a);
        }
        return OP;
    }

    // Check the current listing status
    private static boolean exitCheck() {
        System.out.println("Please enter 1 (move to another list) or 0 (stop making lists).");
        Scanner scan = new Scanner(System.in);
        // Return a boolean response
        return !(scan.next().charAt(0)=='0');
    }

    // Display current arrayList
    public static void printOut(ArrayList a){
        System.out.print("\n Lists:\n");
        int j=0;
        int longest=0;
        for(int i=0; i<a.size();i++){
            LinkedList l = (LinkedList)a.get(i);
            if(longest<l.size()) longest=l.size();
        }
        while(j<longest){
            for(int i=0; i<a.size(); i++){
                LinkedList l = (LinkedList)a.get(i);
                Node n =null;
                if(j<l.size()) n = (Node)l.get(j);
                if(n != null){
                    System.out.println("-> " + n.name + " " + n.cost + " " + n.prob);
                }

            }
            System.out.println();
            j++;
        }
    }

    // Check if there is a test to be done or if the list is full
    private static boolean testCheck(Node node) {
        System.out.println("Enter either: the name of the test or '0' to finish the current list.");
        Scanner scan = new Scanner(System.in);

        String input = scan.next();
        if(input.charAt(0)=='0')
            return false;

        // Or else...
        node.name=input;
        System.out.print("Enter test cost: \n");
        node.cost= scan.nextInt();

        System.out.print("Enter test probability: \n");
        node.prob = scan.nextFloat();

        // if the input does not equal 0...
        return true;
    }

    private static int getMaxLength(ArrayList<LinkedList> aList) {
        int length =0;

        for(int i=0; i < aList.size(); i++){
            LinkedList l = (LinkedList)aList.get(i);
            if(length<l.size()) length=l.size();
        }

        return length;
    }

    // Display current linkedList
    public static void printOut(LinkedList l){
        for(int i=0; i<l.size(); i++){
            Node n = (Node)l.get(i);
            System.out.println("X - " + n.name + " " + n.cost + " " + n.prob);
        }
    }
}