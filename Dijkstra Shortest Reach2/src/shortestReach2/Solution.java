package shortestReach2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;



public class Solution {

	public static ArrayList<Vertex> graph;
	public static Scanner scan;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		graph=new ArrayList<Vertex>();
		scan=new Scanner(System.in);
		int testcase=scan.nextInt();
		
		for(int i=0;i<testcase;i++) {
			int n=scan.nextInt();
			int m=scan.nextInt();
			graphRepresentation(n,m);
			int starting_node=scan.nextInt();
			int [] distance=Dijsktra(starting_node);
		    for (int j = 0; j < distance.length; j++) { 
	            if(distance[j]==Integer.MAX_VALUE) {
	            	System.out.print("-1 ");
	            }else {
	            	if(distance[j]==0) {
	            		
	            	}else {
	            		System.out.print(distance[j]+" "); 
	            }
	           }
            }
	        System.out.println();
			}
		
		
	}
	public static void graphRepresentation(int n,int m) {
        for(int i=0;i<n;i++) {
            Vertex v= new Vertex();
            v.name=i+1;
            graph.add(v);
        }
        for(int i=0;i<m;i++) {
            int u=scan.nextInt();
            int v=scan.nextInt();
            int w=scan.nextInt();
           //starting node 
            Vertex s=new Vertex();
            s.name=u;
            s.weight=w;
           //destination node
            Vertex d=new Vertex();
            d.name=v;
            d.weight=w;
            graph.get(u-1).neighbourList.add(d);
            graph.get(v-1).neighbourList.add(s);
        }
    }
	public static void printGraph() {
        for(int i=0;i<graph.size();i++) {
            System.out.println(graph.get(i).name+"-> ");
            for(int j=0;j<graph.get(i).neighbourList.size();j++) {
                System.out.print(graph.get(i).neighbourList.get(j).name+" W "+graph.get(i).neighbourList.get(j).weight+" ");
            }
            System.out.println();
        }
        
    }
	
	public static int [] Dijsktra(int starting_node) {
		int counter=0;
		Queue<Vertex>queue=new LinkedList<Vertex>();
		Vertex v=graph.get(starting_node-1);
		v.visited=true;
		queue.add(v);	
		int [] distance=new int[graph.size()];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[v.name-1]=0;
		while(queue.isEmpty()==false) {
			int edge_distance=-1;
			int new_distance=-1;
			Vertex s=null;
			s=queue.poll();
			for(int i=0;i<s.neighbourList.size();i++) {
				edge_distance=s.neighbourList.get(i).weight;
				new_distance=distance[s.name-1]+edge_distance;
				if(new_distance<distance[s.neighbourList.get(i).name-1]) {
					distance[s.neighbourList.get(i).name-1]=new_distance;
				}
				if(graph.get(s.neighbourList.get(i).name-1).visited==false) {
					if(queue.add(graph.get(s.neighbourList.get(i).name-1))){
						graph.get(s.neighbourList.get(i).name-1).visited=true;
					}
				}
			
			}
			
		}
		return distance;
	}

}


class Vertex implements Comparable<Vertex>{
	
	int name;
	ArrayList<Vertex> neighbourList;
	int weight;
	boolean visited=false;
	
	public Vertex() {
		neighbourList=new ArrayList<Vertex>();
		
	}

	@Override
	public int compareTo(Vertex vertex) {
		// TODO Auto-generated method stub
		if(this.weight > vertex.weight) {
            return 1;
        } else if (this.weight < vertex.weight) {
            return -1;
        } else {
            return 0;
        }
	
	}
}
