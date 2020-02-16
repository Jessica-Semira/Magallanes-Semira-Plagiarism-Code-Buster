#include<stdio.h> 
#include<string.h> 
#include<stdlib.h> 

void compareFiles(FILE *fp1, FILE *fp2) 
{ 
 
	char ch1 = getc(fp1); 
	char ch2 = getc(fp2); 
	int similar = 0, pos = 0, line = 1; 
	while (ch1 != EOF && ch2 != EOF) 
	{ 
		pos++; 
		if (ch1 == ch2) 
		{ 
			similar++; 
			
}
		ch1 = getc(fp1); 
		ch2 = getc(fp2); 

} 
    char ch, ch3; 
    int wrd=0, wrd2=0, wrd3=0, percentage;
    if(fp1==NULL) 
     { 
         printf(" File does not exist or can not be opened."); 
      } 
    else 
        { 
          ch=fgetc(fp1); 
          while(ch!=EOF) 
            {  
                if(ch==' '||ch=='\n')
                    { 
                        wrd++; 
                    }
                ch=fgetc(fp1); 
        }
        } 
    fclose(fp1); 
   
    if(fp2==NULL) 
     { 
         printf("File does not exist or can not be opened."); 
      } 
    else 
        { 
          ch3=fgetc(fp2); 
          while(ch3!=EOF) 
            { 
                if(ch3==' '||ch3=='\n')
                    { 
                        wrd2++; 
                    }
                ch3=fgetc(fp2); 
        }
        } 
    fclose(fp2); 
    wrd3 = ((((wrd+1)*100)+((wrd2+1)*100)));
    percentage = ((similar%wrd3)*10);
	printf ("The percent difference is %d", percentage);
}

int main() 
{  
	FILE *fp1 = fopen("file1.txt", "r"); 
	FILE *fp2 = fopen("file2.txt", "r"); 

	if (fp1 == NULL || fp2 == NULL) 
	{ 
	printf("Error : Files cannot be found. Please Try Again!"); 
	exit(0); 
	} 

	compareFiles(fp1, fp2); 
	fclose(fp1); 
	fclose(fp2); 

	return 0; 

}

