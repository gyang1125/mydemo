package com.bmw.test.domain;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Class <code>PositionCustomSerializer</code> is to serialise
 * <code>Position</code> object
 * 
 * @author gyang
 *
 */
public class PositionCustomSerializer extends StdSerializer<Position> {
	public PositionCustomSerializer() {
		this(null);
	}

	public PositionCustomSerializer(Class<Position> t) {
		super(t);
	}

	@Override
	public void serialize(Position position, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeStartObject();
		jgen.writeStringField("timestamp", new Timestamp(position.getTimestamp()).toString());
		jgen.writeNumberField("latitude", position.getLatitude());
		jgen.writeNumberField("longitude", position.getLongitude());
		jgen.writeNumberField("heading", position.getHeading());
		jgen.writeStringField("session", position.getSession());
		jgen.writeEndObject();
	}

}
