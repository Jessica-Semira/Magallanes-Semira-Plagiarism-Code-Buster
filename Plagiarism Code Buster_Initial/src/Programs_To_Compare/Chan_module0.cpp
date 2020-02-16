#include<iostream>
#include<string>
#include<fstream>
using namespace std;

int main (){
	ifstream file;
	ifstream file2;
	string line, line2;
	int counterSame;
	int totalLines=0;
	float percentage;
	
	file.open("input.cpp");
	file2.open("input2.cpp");
	
	while (getline(file, line)) {
		bool sameLine(false);
		totalLines++;
		while(getline(file2,line2)){
			if(line == line2){
				sameLine = true;
				counterSame++;
				break;
			}
			
			
		}
		
		file2.clear();
		file2.seekg(0, ios::beg);
		
	}
	
	file.clear();
	file.seekg(0, ios::beg);	

	float variable1 = counterSame;
	float variable2 = totalLines;
	percentage = variable1 / variable2 * 100.0;
	cout<<counterSame<<endl;
	cout<<totalLines<<endl;
	cout<<percentage<<"%" << endl;
	
	file.close();
	file2.close();
  
}
