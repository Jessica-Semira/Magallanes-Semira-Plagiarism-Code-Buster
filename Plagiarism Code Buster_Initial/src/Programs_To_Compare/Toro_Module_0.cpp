#include <iostream>
#include <fstream>
#include <sstream>

#include <string.h>
using namespace std;

int main() {
	double similar = 0, total = 0;
	string line1, line2;
	
	ifstream file1;
	file1.open("test_program1.java");
	
	ifstream file2;
	file2.open("test_program2.java");
	
	while ( getline(file1, line1) ) {
		getline(file2, line2);
		const char *c1 = line1.c_str();
		const char *c2 = line2.c_str();
		if (strcmp(c1, c2) == 0) {
			similar += 1.0;
		}
		total += 1.0;
	}
	file1.close();
	file2.close();
	
	cout << "For test_program1.java and test_program2.java, " << similar << " out of " << total << " lines are the same. (" << similar/total*100 << "%)\n\n";
	
	
	
	similar = 0;
	total = 0;
	
	
	
	file1;
	file1.open("test_program1.cpp");
	
	file2;
	file2.open("test_program2.cpp");
	
	while ( getline(file1, line1) ) {
		getline(file2, line2);
		const char *c1 = line1.c_str();
		const char *c2 = line2.c_str();
		if (strcmp(c1, c2) == 0) {
			similar += 1.0;
		}
		total += 1.0;
	}
	file1.close();
	file2.close();
	
	cout << "For test_program1.cpp and test_program2.cpp, " << similar << " out of " << total << " lines are the same. (" << similar/total*100 << "%)";
}


