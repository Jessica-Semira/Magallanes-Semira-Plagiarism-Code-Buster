//read .cpp as string (stringbuffer ata)
//assign sa variable tapos compare daw :((((

//read code as text file save as variable
//
#include <iostream>
#include <fstream>


using namespace std;

int main(){
	double total=0, total2=0, match=0, percent;
	string line, line2;

  //fstream myfile ("test_program1.java");
  //fstream myfile2 ("test_program2.java");
  fstream myfile ("test_program1.cpp");
  fstream myfile2 ("test_program2.cpp");
  if ((myfile.is_open())&&(myfile2.is_open()))
  {
    while (( getline (myfile,line) )&&(( getline (myfile2,line2) )))
    {
    
    total++;
    total2++;
    if(line.compare(line2)==0){
    	match++;
	}
    }
    myfile.close();
    myfile2.close();
  }
  
  cout<<"Program 1"<<endl;
  cout<<"Total Lines: "<<total<<endl;
  cout<<"\n"<<endl;
  cout<<"Program 2"<<endl;
  cout<<"Total Lines:"<<total2<<endl;

  percent= (match/((total+total2)/2))*100;
  cout<<"Percent Similarity per line: "<<percent<<endl;
  cout<<total<<total<<match;
  /*while(!qLine.empty()){
  	cout<<qLine.front()<<endl;
  	qLine.pop();
  }
  */
  



}
