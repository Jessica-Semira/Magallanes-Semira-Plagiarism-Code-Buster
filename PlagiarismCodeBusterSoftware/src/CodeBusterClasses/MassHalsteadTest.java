package CodeBusterClasses;

import AdditionalJavaFXClasses.HalsteadObject;

import java.io.File;
import java.util.ArrayList;

public class MassHalsteadTest {

    private String directoryCollateProjects;
    private File[] listOfProjects;
    private ArrayList<HalsteadObject> massHalstead;

    public MassHalsteadTest(String directoryCollateProjects){
        this.directoryCollateProjects = directoryCollateProjects;
        listOfProjects = new File(directoryCollateProjects).listFiles();
        massHalstead = new ArrayList<>();

        ExecuteHalstead();
    }

    public ArrayList<HalsteadObject> getHalsteadresults(){return massHalstead;}

    private void ExecuteHalstead(){

        for(File f : listOfProjects){

            HalsteadMetrics halsteadMetrics = new HalsteadMetrics(f.getAbsolutePath());
            massHalstead.add(new HalsteadObject(f.getName(),halsteadMetrics.getProgramLength(),halsteadMetrics.getProgramVocabulary()
            , halsteadMetrics.getProgramDifficulty()));
        }
    }

}
