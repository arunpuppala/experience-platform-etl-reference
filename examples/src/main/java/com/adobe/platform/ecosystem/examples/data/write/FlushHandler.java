
/*
 *  Copyright 2017-2018 Adobe.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.platform.ecosystem.examples.data.write;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * @author vardgupt

 * FlushHandler class contains attributes for flush strategy like
 * size of a record, maximum no. of records which should be put in buffer before flush is called.
 */
public class FlushHandler {

    private long batchSize;
    private final long fileMaxSize = 256*1024*1024l;
    private long sizeOfRecord;
    private long rowsProcessed;
    private List<Object> dataTable;

    public FlushHandler(long sizeOfRecord){
        //Keep size as 3 times the first record
        this.sizeOfRecord = sizeOfRecord*3;
        this.batchSize = fileMaxSize/this.sizeOfRecord;
    }

    public long getBatchSize() {
        return batchSize;
    }

    public long getSizeOfRecord() {
        return sizeOfRecord;
    }

    public long getRowsProcessed() {
        return rowsProcessed;
    }

    public void incrementProcessedRows(long rowsProcessed) {
        this.rowsProcessed+= rowsProcessed;
    }

    public void addRecords(List<?> recordsTobeAdded) {
        if(this.dataTable==null){
            this.dataTable = new ArrayList<>();
        }
        dataTable.addAll(recordsTobeAdded);
    }

    public List<?> getDataTable() {
        return this.dataTable;
    }

    public void reset(){
        this.rowsProcessed = 0;
        this.dataTable.clear();
    }
}