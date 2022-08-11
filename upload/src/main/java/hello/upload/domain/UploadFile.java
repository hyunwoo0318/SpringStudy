package hello.upload.domain;

public class UploadFile {
    private String uploadFileName; // 고객이 올리는 파일명
    private String storeFileName; // 서버에서 저장하는 파일 명 -> 겹치면 안됨.

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
