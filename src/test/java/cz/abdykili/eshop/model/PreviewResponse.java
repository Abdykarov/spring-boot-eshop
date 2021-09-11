package cz.abdykili.eshop.model;

import lombok.Data;

@Data
public class PreviewResponse {
    private String filename;
    private Byte[] bytes;
}
