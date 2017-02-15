package pl.mw;

import java.nio.file.Path;

/**
 * Created by mwisniewski.
 */
public class FDHeader {

    private String username;
    private String md5;
    private String tableName;
    private String columnName;
    private String recordId;
    private long fileSize;
    private String fileMd5;
    private boolean receivedData = false;
    private boolean delete = false;
    private Path path;

    public FDHeader(String raw) {
        String split[] = raw.split("\\|");
        username = split[2];
        md5 = split[3];
        tableName = split[4];
        columnName = split[5];
        recordId = split[6];
        fileSize = Long.valueOf(split[7]);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public boolean isReceivedData() {
        return receivedData;
    }

    public void setReceivedData(boolean receivedData) {
        this.receivedData = receivedData;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
    
    public boolean isUpload() {
        return path != null;
    }

    @Override
    public String toString() {
        return "FDHeader{" +
                "username='" + username + '\'' +
                ", md5='" + md5 + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", recordId='" + recordId + '\'' +
                ", fileSize=" + fileSize +
                ", fileMd5='" + fileMd5 + '\'' +
                ", receivedData=" + receivedData +
                ", delete=" + delete +
                ", path=" + path +
                '}';
    }
}