//io/test1.cpp

#include <iostream>
#include <iomanip>
#include <fstream>
using namespace std;
//assign ung string sa isang variable then compare
//ano metric 
int main(){
	string line1[30];
	string line2[30];
	string line3[30];
	string line4[30];
	string line;
	int a = 0;
	int b = 0;
	int c = 0;
	int d = 0;
	
	int total = 7;
	
	ifstream inFile;
	ifstream inFile2;
	ifstream JavaFile1;
	ifstream JavaFile2;
	
	inFile.open("test1.cpp",ios::in);
	cout <<"File: test1.cpp"<<'\n';
	if(inFile.is_open()){
		while(!inFile.eof()){

			getline(inFile,line1[a],'\n');
			cout<<line1[a]<<'\n';
			a++;
		}
		inFile.close();
	}
	
	
	else cout<< "Wrong.";
	
	///////////////////////////////////////////////
	inFile2.open("test2.cpp",ios::in);
	cout <<'\n';
	cout <<"File: test2.cpp"<<'\n';
	if(inFile2.is_open()){
		while(!inFile2.eof()){
			getline(inFile2,line2[b],'\n');
			cout<<line2[b]<<'\n';
			b++;
		}
		inFile2.close();
	}
	else cout<< "Wrong.";
	
	
	///////////////////////////////////////////////////////
	int totalsim = 100;
	for(int i=0;i<total;i++){
		if(line1[i]==line2[i])continue;
		else{
			totalsim = totalsim - 1;	
		}
	}
	
	cout << "Similarity Percentage: ";
	cout << totalsim<<'\n';
	cout <<'\n';
	////////////////////////////////////////////////////////
	////java//
	
	JavaFile1.open("test_program1.java",ios::in);
	cout <<"test_program1.java"<<'\n';
	if(JavaFile1.is_open()){
		while(!JavaFile1.eof()){
			getline(JavaFile1,line3[c],'\n');
			cout<<line3[c]<<'\n';
			c++;
		}
		JavaFile1.close();
	}
	else cout<< "Wrong.";
	cout <<'\n';
	JavaFile2.open("test_program2.java",ios::in);
	cout <<"test_program2.java"<<'\n';
	if(JavaFile2.is_open()){
		while(!JavaFile2.eof()){
			getline(JavaFile2,line4[d],'\n');
			cout<<line4[d]<<'\n';
			d++;
		}
		JavaFile2.close();
	}
	else cout<< "Wrong.";
	
	///////////////////////////////////////////////////////
	int totalsim2 = 100;
	int total2 = 5;
	for(int i=0;i<total2;i++){
		if(line3[i]==line4[i])continue;
		else{
			totalsim2 = totalsim2 - 1;	
		}
	}
	
	cout << "Similarity Percentage: ";
	cout << totalsim2<<'\n';
	cout <<'\n';
	
	
}
