#include <iostream>
#include <fstream>
#include<stdio.h>
#include<string.h>
using namespace std;

int main(){
	
	/*File Reading*/
	
	// Create a text string, which is used to output the text file
	string myTextAlpha;
	string myTextBravo;
	
	//--------------------------------------------------------------

	/*Read from the text file Alpha*/
	ifstream MyReadFileAlpha("test_program1.cpp");
	
	// Use a while loop together with the getline() function to read the file line by line
  	while (getline (MyReadFileAlpha, myTextAlpha)) {
    // Output the text from the file
    cout << myTextAlpha;
  	}
  	
  	//Use to separate the two strings into two lines
  	cout << "" << endl;
  	
  	//--------------------------------------------------------------
  	
  	/*Read from the text file Bravo*/
	ifstream MyReadFileBravo("test_program2.cpp");
	
	// Use a while loop together with the getline() function to read the file line by line
  	while (getline (MyReadFileBravo, myTextBravo)) {
    // Output the text from the file
    cout << myTextBravo;
  	}
  	
  	cout << "" << endl;
	
	//--------------------------------------------------------------
	
	std::string a = myTextAlpha;
	std::string b = myTextBravo;
	
	//--------------------------------------------------------------
	
	char str1[a.size() + 1];
	char str2[b.size() + 1];
	
	//--------------------------------------------------------------
	
	strcpy(str1, a.c_str());
	strcpy(str2, b.c_str());
	
	//--------------------------------------------------------------
	
	/* String comparison (strcmp function)*/
	
	int result = strcmp(str1, str2);
	
	if (result == 0)
    	printf("Program is equal");
	else
    	printf("Program is not equal");

	printf("\nValue returned by strcmp() is:  %d" , result);
	
	return 0;
}
