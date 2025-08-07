package com.praxium.prepengine.modal.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PromptRequest {
    private String mode; // "cat-batch", "case-study", or null
    private String product; // "NCLEX", "SAT", etc.
    private Integer level;
    private Integer catBatchSize;
    private Integer questionsToFetch;
    private String topic;

}
