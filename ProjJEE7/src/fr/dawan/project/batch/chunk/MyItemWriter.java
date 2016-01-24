package fr.dawan.project.batch.chunk;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;
import javax.inject.Named;

@Named
public class MyItemWriter implements ItemWriter {

	@Override
	public Serializable checkpointInfo() throws Exception {
		System.out.println("**** MyItemWriter::checkpointInfo");
		return null;
	}

	@Override
	public void close() throws Exception {
		System.out.println("**** MyItemWriter::close");

	}

	@Override
	public void open(Serializable checkpoint) throws Exception {
		System.out.println("**** MyItemWriter::open");

	}

	@Override
	public void writeItems(List<Object> items) throws Exception {
		System.out.println("**** MyItemWriter::writeItems");

	}

}
