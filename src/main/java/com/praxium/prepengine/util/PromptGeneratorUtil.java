package com.praxium.prepengine.util;

import com.praxium.prepengine.modal.request.PromptRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PromptGeneratorUtil {

    public static String buildPrompt(PromptRequest request) {
        String mode = request.getMode();
        String product = request.getProduct() != null ? request.getProduct() : "NCLEX";
        int level = request.getLevel() != null ? request.getLevel() : 5;
        int batchSize = request.getCatBatchSize() != null ? request.getCatBatchSize() : 10;
        int questionsToFetch = request.getQuestionsToFetch() != null ? request.getQuestionsToFetch() : 10;
        String topic = request.getTopic();

        if ("cat-batch".equalsIgnoreCase(mode)) {
            return String.format("""
                    Generate %d unique, high-quality %s-style questions at difficulty level %d/10.
                    Include a mix of standard multiple-choice (MCQ) and 'Select All That Apply' (SATA) questions.
                    Return a single valid JSON object with a key "questions", which is an array containing exactly %d question objects.
                    Each object must have keys: "question", "questionType" ('MCQ' or 'SATA'), "options" (array of 4-6 strings),
                    "correctAnswerIndex" (integer for MCQ, array of integers for SATA), "category", and "rationale"
                    (an object with keys 'correct' and 'incorrect').
                    """, batchSize, product, level, batchSize).trim();

        } else if ("aptitude".equalsIgnoreCase(mode)) {
            return String.format("""
                    Generate %d unique, high-quality %s-style questions with mix difficulty level %d/10. Keep in mind that
                    the set of question should be in such a way that we can assess the users proficiency on this particular
                    context based on how much any user scores.
                    Include a mix of standard multiple-choice (MCQ) and 'Select All That Apply' (SATA) questions.
                    Return a single valid JSON object with a key "questions", which is an array containing exactly %d question objects.
                    Each object must have keys: "question", "questionType" ('MCQ' or 'SATA'), "options" (array of 4-6 strings),
                    "correctAnswerIndex" (integer for MCQ, array of integers for SATA), "category", and "rationale"
                    (an object with keys 'correct' and 'incorrect').
                    """, batchSize, product, level, batchSize).trim();
        }
        else if ("case-study".equalsIgnoreCase(mode)) {
                return String.format("""
                        Generate a detailed %s-style patient case study scenario. The scenario should include patient demographics,
                        presenting symptoms, vital signs, and relevant medical history. Following the scenario, generate 3-5 critical
                        thinking questions related to the case. The entire output must be a single valid JSON object with two top-level
                        keys: "scenario" (a string containing the detailed patient case) and "questions" (an array of the question objects).
                        Each question object must have the standard keys: "question", "questionType", "options", "correctAnswerIndex",
                        "category", and "rationale".
                        """, product).trim();
            }

            // Default/general prompt
            StringBuilder basePrompt = new StringBuilder();
            basePrompt.append(String.format(
                    "Generate %d unique %s-style questions at difficulty level %d/10. ",
                    questionsToFetch, product, level
            ));

            if (topic != null && !topic.isBlank()) {
                basePrompt.append("All questions must be from the category: ").append(topic).append(". ");
            } else {
                basePrompt.append("Cover a range of relevant categories.");
            }

            basePrompt.append(String.format("""
                    Include a mix of standard multiple-choice (MCQ) and 'Select All That Apply' (SATA) questions.
                    Return a single valid JSON object with a key "questions", which is an array of %d question objects.
                    Each object must have keys: "question", "questionType" ('MCQ' or 'SATA'), "options" (array of 4-6 strings),
                    "correctAnswerIndex" (integer for MCQ, array of integers for SATA), "category", and "rationale"
                    (an object with keys 'correct' and 'incorrect').
                    """, questionsToFetch));

            return basePrompt.toString().trim();
        }
    }

