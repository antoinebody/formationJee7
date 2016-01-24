package fr.dawan.project.batch.chunk;

import java.io.Serializable;

import javax.batch.api.chunk.ItemReader;
import javax.inject.Named;

@Named
public class MyItemReader implements ItemReader {

	@Override
	public Serializable checkpointInfo() throws Exception {
		System.out.println("**** MyItemReader::checkpointInfo");
		return null;
	}

	@Override
	public void close() throws Exception {
		System.out.println("**** MyItemReader::close");

	}

	@Override
	public void open(Serializable checkpoint) throws Exception {
		System.out.println("**** MyItemReader::open");

	}

	@Override
	public Object readItem() throws Exception {
		System.out.println("**** MyItemReader::readItem");
		return "Hello from MyItemReader";
	}

}
