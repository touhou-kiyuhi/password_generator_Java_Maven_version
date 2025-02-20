package com.example.json_classes.controller;

import java.io.IOException;

import com.example.json_classes.format.JsonType;
import com.example.json_classes.settings.JsonViewerType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

public interface Controller {

    // Reader 
    public Object jsonReader(String path, JsonType jsonType) throws StreamReadException, DatabindException, IOException;
    // Writer 
    public void jsonWriter(String path, Object data) throws StreamWriteException, DatabindException, IOException;
    // Viewer 
    public void jsonViewer(Object data, JsonViewerType jsonviewerType) throws JsonProcessingException;
}
