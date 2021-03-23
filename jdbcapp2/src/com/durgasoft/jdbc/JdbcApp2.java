package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcApp2 {

	public static void main(String[] args)throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
		Statement st=con.createStatement();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Table name:");
		String tname=br.readLine();
		String query="create table "+tname+"(";
		String primaryKeys="";
		int primaryKeyCount=0;
		while(true){
			System.out.println("Column Name:");
			String colName=br.readLine();
			System.out.println("Column Type:");
			String colType=br.readLine();
			System.out.println("Column Size:");
			int colSize=Integer.parseInt(br.readLine());
			query=query+colName+" "+colType+"("+colSize+")";
			System.out.println("Is Promary key[yes/no]? :");
			String primaryKeyOption=br.readLine();
			if(primaryKeyOption.equalsIgnoreCase("yes")){
				if(primaryKeyCount==0)
				{
					primaryKeys=primaryKeys+colName;
					primaryKeyCount=primaryKeyCount+1;
				}
				else{
					primaryKeys=primaryKeys+" ,"+colName;
					
				}
			}
			System.out.println("One More Column[yes/no]?  :");
			String columnOption=br.readLine();
			if(columnOption.equalsIgnoreCase("yes"))
			{
				query=query+",";
				continue;
				
			}
			else{
				query=query+" ,primary key("+ primaryKeys + "))";
				break;
			}
		}
		st.executeUpdate(query);
		
        System.out.println("table" +tname+ "created succesfully");
	}

}
