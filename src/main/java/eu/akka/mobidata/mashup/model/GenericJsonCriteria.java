package eu.akka.mobidata.mashup.model;

public class GenericJsonCriteria {
    private String attributesParentPath;

    private String[] enrichAttributesRootPaths = new String[0];
    private String[] namePaths = new String[0];
    private String[] coordsPaths = new String[0];

    public GenericJsonCriteria(String coordsPath, String namePath, String attributesParentPath) {
        initializePaths(coordsPath, namePath, attributesParentPath);
    }

    private void initializePaths(String coordsPath, String namePath, String attributesParentPath) {
        this.attributesParentPath = attributesParentPath;

        int index = coordsPath.indexOf(attributesParentPath);
        if (index == -1) {
            throw new RuntimeException("Unsupported Data Format! The path of the coordinates must contain the root attributes path");
        }
        if (!coordsPath.equals(attributesParentPath)) {
            coordsPaths = coordsPath.replace(attributesParentPath + ".", "").split("\\.");
        }

        index = namePath.indexOf(attributesParentPath);
        if (index == -1) {
            throw new RuntimeException("Unsupported Data Format! The path of the name must contain the root attributes path");
        }
        if (!namePath.equals(attributesParentPath)) {
            namePaths = namePath.replace(attributesParentPath + ".", "").split("\\.");
        }
    }

    public GenericJsonCriteria(String coordsPath, String namePath, String attributesParentPath, String sourceEnrichAttributesRootPath) {
        initializePaths(coordsPath, namePath, attributesParentPath);

        int index = sourceEnrichAttributesRootPath.indexOf(attributesParentPath);
        if (index == -1) {
            throw new RuntimeException("Unsupported Data Format! The path of the name must contain the root attributes path");
        }
        if (!sourceEnrichAttributesRootPath.equals(attributesParentPath)) {
            enrichAttributesRootPaths = sourceEnrichAttributesRootPath.replace(attributesParentPath + ".", "").split("\\.");
        }
    }

    public String getAttributesParentPath() {
        return attributesParentPath;
    }

    public String[] getNamePaths() {
        return namePaths;
    }

    public String[] getCoordsPaths() {
        return coordsPaths;
    }

    public String[] getEnrichAttributesRootPaths() {
        return enrichAttributesRootPaths;
    }
}
