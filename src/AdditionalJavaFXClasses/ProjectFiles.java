package AdditionalJavaFXClasses;

public class ProjectFiles {

    private Integer index;
    private String projectFileName;

    public ProjectFiles(int index , String projectFileName){
        this.index = index;
        this.projectFileName = projectFileName;
    }

    public Integer getIndex() {
        return index;
    }

    public String getProjectFileName() {
        return projectFileName;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setProjectFileName(String projectFileName) {
        this.projectFileName = projectFileName;
    }
}
