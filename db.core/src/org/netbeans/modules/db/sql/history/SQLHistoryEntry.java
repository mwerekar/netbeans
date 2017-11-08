/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.db.sql.history;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "sql")
public class SQLHistoryEntry {

    private String url;
    private String sql;
    private Date date;

    protected SQLHistoryEntry() {
    }

    public SQLHistoryEntry(String url, String sql, Date date) {
        this.url = url;
        this.sql = sql;
        this.date = date;
    }

    @XmlTransient
    public Date getDate() {
        return date;
    }

    protected void setDate(Date date) {
        this.date = date;
    }

    @XmlValue
    public String getSql() {
        return sql;
    }

    protected void setSql(String sql) {
        if (sql != null) {
            this.sql = sql.trim();
        } else {
            this.sql = sql;
        }
    }

    @XmlAttribute
    public String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    @XmlAttribute(name = "date")
    protected String getDateXMLVariant() {
        if (this.date == null) {
            return null;
        } else {
            return Long.toString(date.getTime());
        }
    }

    protected void setDateXMLVariant(String value) {
        try {
            date = new Date(Long.parseLong(value));
        } catch (NumberFormatException nfe) {
            // #152486 - previously date stored in text format
            try {
                date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).parse(value);
            } catch (ParseException pe) {
                // # 152486; Date stored is not parsable, so reset the date to the current timestamp
                date = new Date();
            }
        }

    }

    @Override
    public String toString() {
        return "SQLHistoryEntry{" + "url=" + url + ", sql=" + sql + ", date=" + date + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SQLHistoryEntry other = (SQLHistoryEntry) obj;
        if ((this.url == null) ? (other.url != null) : !this.url.toLowerCase().equals(other.url.toLowerCase())) {
            return false;
        }
        if ((this.sql == null) ? (other.sql != null) : !this.sql.toLowerCase().equals(other.sql.toLowerCase())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.sql != null ? this.sql.toLowerCase().hashCode() : 0);
        return hash;
    }
}
