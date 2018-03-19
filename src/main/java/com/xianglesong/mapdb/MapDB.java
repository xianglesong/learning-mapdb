package com.xianglesong.mapdb;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

/**
 * Created by rollin on 18/3/19.
 */
public class MapDB {

    public static void main(String[] args) {
//        DB db = DBMaker.memoryDB().make();
        
        DB db = DBMaker.fileDB("file.db")
                .fileMmapEnable()
                .fileMmapEnableIfSupported()
                .fileMmapPreclearDisable()
                .closeOnJvmShutdown()
                .cleanerHackEnable()
                .concurrencyScale(8)
                .make();

        HTreeMap<String, String> hTreeMap = db.hashMap("file_map")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.STRING)
                .createOrOpen();

        hTreeMap.put("1", "{[2011-01-01,123],[2011-01-01,123]}");
        hTreeMap.put("2", "{[2011-01-01,123],[2011-01-01,123]}");

        System.out.println(hTreeMap.get("1"));
        System.out.println(hTreeMap.get("11"));

        db.close();
    }
}
