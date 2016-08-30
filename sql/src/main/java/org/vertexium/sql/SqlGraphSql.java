package org.vertexium.sql;

import org.vertexium.*;
import org.vertexium.property.StreamingPropertyValue;
import org.vertexium.sql.models.PropertyValueBase;
import org.vertexium.sql.models.SqlGraphValueBase;
import org.vertexium.sql.utils.RowType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.List;

public interface SqlGraphSql {
    Connection getConnection() throws SQLException;

    void createTables();

    void truncate();

    long insertStreamingPropertyValue(Connection conn, StreamingPropertyValue spv);

    void insertElementRow(
            Connection conn,
            ElementType elementType,
            String elementId,
            RowType rowType,
            long timestamp,
            Visibility visibility,
            SqlGraphValueBase value
    );

    Iterable<Edge> selectAllEdges(SqlGraph graph, EnumSet<FetchHint> fetchHints, Long endTime, Authorizations authorizations);

    Edge selectEdge(SqlGraph graph, String edgeId, EnumSet<FetchHint> fetchHints, Long endTime, Authorizations authorizations);

    Iterable<Vertex> selectAllVertices(SqlGraph graph, EnumSet<FetchHint> fetchHints, Long endTime, Authorizations authorizations);

    Vertex selectVertex(SqlGraph graph, String vertexId, EnumSet<FetchHint> fetchHints, Long endTime, Authorizations authorizations);

    Iterable<GraphMetadataEntry> metadataSelectAll();

    void metadataSetMetadata(String key, Object value);

    StreamingPropertyValue selectStreamingPropertyValue(long spvRowId);

    void deletePropertyRows(Connection conn, ElementType elementType, String elementId, String propertyKey, String propertyName, Visibility propertyVisibility);

    void deleteElementRows(Connection conn, ElementType elementType, String elementId);

    void deleteVertexEdgeRows(Connection conn, String vertexId, String edgeId);

    List<PropertyValueBase> selectAllValues(
            Connection conn,
            ElementType elementType,
            String elementId,
            Long startTime,
            Long endTime,
            Authorizations authorizations
    );
}