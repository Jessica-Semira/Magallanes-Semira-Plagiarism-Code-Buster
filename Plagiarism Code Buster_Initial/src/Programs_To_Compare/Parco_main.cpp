#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

class wordAmount{
public:

    struct wordCount{
        string word;
        int count = 0;
    };

    vector<string> currentWords;
    vector<wordCount> currentWordsAmount;
    vector<string> currentWordsRepeat;
    vector<string> lines;
    vector<float> lineDiff;

    void insertWord(string word){
        auto it = find(currentWords.begin(), currentWords.end(), word);
        int dist = distance(currentWords.begin(), it);
        if(it != currentWords.end()){
            currentWordsAmount[dist].count++;
        }
        else{
            wordCount word2;
            word2.count++;
            word2.word = word;
            currentWords.push_back(word);
            currentWordsAmount.push_back(word2);
        }
        currentWordsRepeat.push_back(word);
    }

    void printWords(){
        int x, n;
        n = currentWords.size();
        for(x = 0; x < n; x++){
            cout << currentWords[x] << "\t" << currentWordsAmount[x].count << endl;
        }
        cout << endl;
    }

    void loadFile(string filename){
        ifstream file(filename);
        string word;
        while(!file.eof()){
            file >> word;
            this->insertWord(word);
        }
        file.close();
    }

    void loadFileLines(string filename){
        ifstream file(filename);
        string line;
        while(!file.eof()){
            getline(file, line, '\n');
            lines.push_back(line);
        }
        file.close();
    }

    void loadFileDebug(string filename){
        ifstream file(filename);
        string word;
        while(!file.eof()){
            file >> word;
            this->insertWord(word);
            this->printWords();
        }
        file.close();
    }

    int size(){
        return currentWordsRepeat.size();
    }

    int getStringValue(string word){
        int s = word.size();
        int amt = 0;
        const char* cstr = word.c_str();
        for(int x = 0; x < s; x++){
            amt = amt + cstr[x];
        }
        return amt;
    }

    int vecStringValue(){
        int x, n;
        vector<wordCount> temp = this->currentWordsAmount;
        sort(temp.begin(), temp.end(), [](wordCount a, wordCount b){
                return a.word > b.word;
            }
        );
        n = temp.size();
        string str;
        int amt = 0;
        int curr;
        for(x = 0; x < n; x++){
            //cout << currentWords[x] << "\t" << currentWordsAmount[x].count << endl;
            str = temp[x].word;
            curr = this->getStringValue(str);
            curr = curr*temp[x].count;
            amt = amt + curr;
        }
        return amt;
    }

    int wordSimilarity(wordAmount comp){
        int count = 0;
        auto a = this->currentWordsRepeat;
        auto b = comp.currentWordsRepeat;
        int size = a.size();
        if(a.size() > b.size()) size = b.size();
        sort(b.begin(), b.end());
        sort(a.begin(), a.end());
        for(int x = 0; x<size; x++){
            if(a[x].compare(b[x])==0) count++;
        }
        return count;
    }

    float getLineDiff(wordAmount comp){
        auto a = this->lines;
        auto b = comp.lines;
        int size = a.size();
        float diff=0;
        if(a.size() > b.size()) size = b.size();
        for(int x = 0; x<size; x++){
            lineDiff.push_back(getStringValue(a[x])-getStringValue(b[x]));
            diff = diff + getStringValue(a[x])-getStringValue(b[x]);
        }
        return diff;
    }
};

float getPercentDiff(int a, int b, int diff);

int main()
{
    {
        wordAmount a, b;
        int aI, bI;
        float temp;
        a.loadFile("filesToRead/0/test_program1.java");
        b.loadFile("filesToRead/0/test_program2.java");
        aI = a.vecStringValue();
        bI = b.vecStringValue();
        cout << "Pair 1" << endl << endl;
        cout << "String int value metric:" << endl;
        cout << aI << "\t" << bI << endl;
        cout << "percent difference: "<< 100*getPercentDiff(aI, bI, aI-bI) << endl;
        cout << "percent similarity: "<< 100*(1-getPercentDiff(aI, bI, aI-bI)) << endl;
        cout << endl;
        cout << "String equality metric:" << endl;
        cout << a.wordSimilarity(b) << "/" << to_string(a.size()) << endl;
        temp =((float)a.wordSimilarity(b)/(float)a.size());
        cout << temp*100 << "%" << endl;
    }
    cout << endl << endl;
    {
        wordAmount a, b;
        int aI, bI;
        float temp;
        a.loadFile("filesToRead/1/test_program1.cpp");
        b.loadFile("filesToRead/1/test_program2.cpp");
        aI = a.vecStringValue();
        bI = b.vecStringValue();
        cout << "Pair 2" << endl << endl;
        cout << "String int value metric:" << endl;
        cout << aI << "\t" << bI << endl;
        cout << "percent difference: "<< 100*getPercentDiff(aI, bI, aI-bI) << endl;
        cout << "percent similarity: "<< 100*(1-getPercentDiff(aI, bI, aI-bI)) << endl;
        cout << endl;
        cout << "String equality metric:" << endl;
        cout << a.wordSimilarity(b) << "/" << to_string(a.size()) << endl;
        temp =((float)a.wordSimilarity(b)/(float)a.size());
        cout << temp*100 << "%" << endl;
    }
    /*
    a.insertWord("word");
    a.printWords();
    a.insertWord("word");
    a.insertWord("word");
    a.printWords();
    a.insertWord("amount");
    a.printWords();
    */
}

float getPercentDiff(int a, int b, int diff){
    float avg = (a+b)/2;
    float fDiff = (float) diff;
    return fDiff/avg;
}
