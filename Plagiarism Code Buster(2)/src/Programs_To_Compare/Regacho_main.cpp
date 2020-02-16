#include <iostream>
#include <fstream>

using namespace std;

int main(){
    
	float f=0, c=0, j=0;
    ifstream file1("test_program1.cpp");
    ifstream file2("test_program2.cpp");
    string line1, line2;
    file1 >> line1;
    file2 >> line2;
    
    while (getline(file1,line1)&&getline(file2,line2)) {
        if(line1==line2){
            c++;
        }
        j++;
    }
    
    f = ((c/j)*100);
    cout << "Similarity Percentage: " << f <<"%";
    
    file1.close();
    file2.close();

    return 0;
    
}
