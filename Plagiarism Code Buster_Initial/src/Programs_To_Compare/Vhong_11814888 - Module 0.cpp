#include <iostream>
#include <string.h>

using namespace std;

int main(){
	
	char ch, file1[50] = "test_program1.cpp", file2[50] = "test_program2.cpp";
	string content1 = "",content2 = "", streak = "";
	FILE *fp;
	
	fp = fopen(file1,"r");
	
	while ((ch = fgetc(fp)) != EOF){
		content1 += ch;
	}
	cout << "First file:" << endl << endl << content1 << endl;
	
	fclose(fp);
	
	fp = fopen(file2,"r");
	
	while ((ch = fgetc(fp)) != EOF){
		content2 += ch;
	}
	cout << endl << "Second File: " << endl << endl << content2 << endl;
	
	fclose(fp);
	
	double equalcounter = 0;
	double notequalcounter = 0;
	
	int length;
	
	if (content1.length() > content2.length()){
		length = content1.length();
	}
	else {
		length = content2.length();
	}
	
	for (int i = 0; i < length; i++){
		if (content1[i] == content2[i]){
			equalcounter++;
		}
		else if (content1[i] != content2[i]){
			notequalcounter++;
		}
	}
	
	for (int i = 0; i < equalcounter; i++){
		streak = streak + content1[i];
	}
	
	cout << endl << "Longest similarity streak" << endl << endl << streak;
	
	double total = equalcounter + notequalcounter;
	
	double comparison = equalcounter / total * 100;
	
	cout << endl << endl << "Percentage Comparison:" << endl << comparison << "%" << endl; 
}
