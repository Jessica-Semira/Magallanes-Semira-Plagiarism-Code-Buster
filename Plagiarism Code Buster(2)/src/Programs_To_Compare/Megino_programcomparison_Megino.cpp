#include <iostream>
#include<fstream>
#include<string>
#include<queue>
using namespace std;


int main(){
	fstream test1;
	fstream test2;
	string line;
	
	queue<string> qtest1;
	queue<string> qtest2;
	
	queue<string> test1print;
	queue<string> test2print;
	

	//test1.open("test_program1.cpp");
	//test2.open("test_program2.cpp");
	test1.open("test_program1.java");
	test2.open("test_program2.java");
	
	int test1count=0,test2count=0,same=0;
	float average=0;
	float testtotal=0;
	float sametotal=0;
	//test1.open("test_program1.cpp");
	//qtest1.push("1");
	//qtest1.push("2");
	
	
	
	if (test1.is_open()){
		while(getline(test1,line)){
			qtest1.push(line);
			test1count++;
		}
		test1.close();
	}
	else cout<<"test 1 is not opening"<<"\t";
	
	
	if (test2.is_open()){
		while(getline(test2,line)){
			qtest2.push(line);
			test2count++;
		}
		test2.close();
	}
	else cout<<"test 2 is not opening"<<"\t";
	
	
	test1print=qtest1;
	cout<<"Test 1:"<<"\n";
	while(!test1print.empty()){
		cout<<test1print.front()<<"\n";
		test1print.pop();
	}
	cout<<"\n";
	
	test2print=qtest2;
	cout<<"Test 2:"<<"\n";
	while (!test2print.empty()){
		cout<<test2print.front()<<"\n";
		test2print.pop();
	}
	
	cout<<"\n";
	
	while(!qtest1.empty()&&!qtest2.empty()){
		if(qtest1.front()==qtest2.front()) same++;
		qtest1.pop();
		qtest2.pop();
	}
	
	
	testtotal=test1count+test2count;
	sametotal=same*2;
	average=(sametotal/testtotal)*100;
	
	cout<<"Number of lines in Test 1: "<<test1count<<"\n";
	cout<<"Number of lines in Test 2: "<<test2count<<"\n";
	cout<<"Number of same lines: "<<same<<"\n";
	cout<<"Similarity Percentage: "<<average<<"%";
	
	
	
	
	
}
