package com.neo.fileupload.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseData {

    private String fileName;
    private String downloadURL;
    private String fileType;
    private Long fileSize;
}
