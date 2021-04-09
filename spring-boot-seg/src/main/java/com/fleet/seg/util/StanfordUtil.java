package com.fleet.seg.util;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

import java.util.List;
import java.util.Properties;

/**
 * Stanford 分词器
 *
 * @author April Han
 */
public class StanfordUtil {

    public static List<String> seg(String text) {
        Properties properties = new Properties();
        properties.setProperty("sighanCorporaDict", "data");
        properties.setProperty("serDictionary", "data/dict-chris6.ser.gz");
        properties.setProperty("inputEncoding", "UTF-8");
        properties.setProperty("sighanPostProcessing", "true");
        CRFClassifier<CoreLabel> crfClassifier = new CRFClassifier<>(properties);
        crfClassifier.loadClassifierNoExceptions("data/ctb.gz", properties);
        crfClassifier.flags.setProperties(properties);
        return crfClassifier.segmentString(text);
    }
}
