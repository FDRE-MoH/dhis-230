package org.hisp.dhis.dxf2.datavalueset;

/*
 * Copyright (c) 2004-2018, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.IdScheme;
import org.hisp.dhis.dxf2.datavalue.DataValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

/**
 * @author Lars Helge Overland
 */
@JacksonXmlRootElement( localName = "dataValueSet", namespace = DxfNamespaces.DXF_2_0 )
public class DataValueSet
{
    protected static final String FIELD_IDSCHEME = "idScheme";
    protected static final String FIELD_DATAELEMENTIDSCHEME = "dataElementIdScheme";
    protected static final String FIELD_ORGUNITIDSCHEME = "orgUnitIdScheme";
    protected static final String FIELD_CATEGORYOPTCOMBOIDSCHEME = "categoryOptionComboIdScheme";
    protected static final String FIELD_DATASETIDSCHEME = "dataSetIdScheme";
    protected static final String FIELD_DRYRUN = "dryRun";
    protected static final String FIELD_IMPORTSTRATEGY = "importStrategy";

    protected static final String FIELD_DATAVALUESET = "dataValueSet";
    protected static final String FIELD_DATAVALUE = "dataValue";
    protected static final String FIELD_COMPLETEDATASET = "completeDataSet"; 
    protected static final String FIELD_DATASET = "dataSet";
    protected static final String FIELD_COMPLETEDATE = "completeDate";
    protected static final String FIELD_PERIOD = "period";
    protected static final String FIELD_ORGUNIT = "orgUnit";
    protected static final String FIELD_ATTRIBUTE_OPTION_COMBO = "attributeOptionCombo";

    //--------------------------------------------------------------------------
    // Options
    //--------------------------------------------------------------------------

    protected String idScheme;

    protected String dataElementIdScheme;

    protected String orgUnitIdScheme;
    
    protected String categoryOptionComboIdScheme;
    
    protected String dataSetIdScheme;

    protected Boolean dryRun;

    protected String strategy;

    //--------------------------------------------------------------------------
    // Properties
    //--------------------------------------------------------------------------

    protected List<CompleteDataSet> completeDataSets = new ArrayList<>();

    protected List<DataValue> dataValues = new ArrayList<>();

    protected List<String> attributeCategoryOptions;

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    public DataValueSet()
    {
    }

    //--------------------------------------------------------------------------
    // Getters and setters
    //--------------------------------------------------------------------------

    @JsonProperty
    @JacksonXmlProperty( isAttribute = true )
    public String getIdScheme()
    {
        return idScheme;
    }

    public void setIdScheme( String idScheme )
    {
        this.idScheme = idScheme;
    }

    @JsonProperty
    @JacksonXmlProperty( isAttribute = true )
    public String getDataElementIdScheme()
    {
        return dataElementIdScheme;
    }

    public void setDataElementIdScheme( String dataElementIdScheme )
    {
        this.dataElementIdScheme = dataElementIdScheme;
    }

    @JsonProperty
    @JacksonXmlProperty( isAttribute = true )
    public String getOrgUnitIdScheme()
    {
        return orgUnitIdScheme;
    }

    public void setOrgUnitIdScheme( String orgUnitIdScheme )
    {
        this.orgUnitIdScheme = orgUnitIdScheme;
    }

    @JsonProperty
    @JacksonXmlProperty( isAttribute = true )
    public String getCategoryOptionComboIdScheme()
    {
        return categoryOptionComboIdScheme;
    }

    public void setCategoryOptionComboIdScheme( String categoryOptionComboIdScheme )
    {
        this.categoryOptionComboIdScheme = categoryOptionComboIdScheme;
    }

    @JsonProperty
    @JacksonXmlProperty( isAttribute = true )
    public String getDataSetIdScheme()
    {
        return dataSetIdScheme;
    }

    public void setDataSetIdScheme( String dataSetIdScheme )
    {
        this.dataSetIdScheme = dataSetIdScheme;
    }

    @JsonProperty
    @JacksonXmlProperty( isAttribute = true )
    public Boolean getDryRun()
    {
        return dryRun;
    }

    public void setDryRun( Boolean dryRun )
    {
        this.dryRun = dryRun;
    }

    @JsonProperty
    @JacksonXmlProperty( isAttribute = true )
    public String getStrategy()
    {
        return strategy;
    }

    public void setStrategy( String strategy )
    {
        this.strategy = strategy;
    }

    @JsonProperty( value = "completeDataSets" )
    @JacksonXmlElementWrapper( localName = "completeDataSets", useWrapping = false, namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "completeDataSet", namespace = DxfNamespaces.DXF_2_0 )
    public List<CompleteDataSet> getCompleteDataSets()
    {
        return completeDataSets;
    }

    public void setCompleteDataSets( List<CompleteDataSet> completeDataSets )
    {
        this.completeDataSets = completeDataSets;
    }

    @JsonProperty( value = "dataValues" )
    @JacksonXmlElementWrapper( localName = "dataValues", useWrapping = false, namespace = DxfNamespaces.DXF_2_0 )
    @JacksonXmlProperty( localName = "dataValue", namespace = DxfNamespaces.DXF_2_0 )
    public List<DataValue> getDataValues()
    {
        return dataValues;
    }

    public void setDataValues( List<DataValue> dataValues )
    {
        this.dataValues = dataValues;
    }

    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public List<String> getAttributeCategoryOptions()
    {
        return attributeCategoryOptions;
    }

    public void setAttributeCategoryOptions( List<String> attributeCategoryOptions )
    {
        this.attributeCategoryOptions = attributeCategoryOptions;
    }

    //--------------------------------------------------------------------------
    // Logic
    //--------------------------------------------------------------------------

    private Iterator<DataValue> dataValueIterator;

    public void refreshDataValueIterator()
    {
        dataValueIterator = dataValues.iterator();
    }

    public boolean hasNextDataValue()
    {
        if ( dataValueIterator == null )
        {
            refreshDataValueIterator();
        }

        return dataValueIterator.hasNext();
    }

    public DataValue getNextDataValue()
    {
        if ( dataValueIterator == null )
        {
            refreshDataValueIterator();
        }

        return dataValueIterator.next();
    }

    public DataValue getDataValueInstance()
    {
        return new DataValue();
    }
    
    private Iterator<CompleteDataSet> completeDataSetIterator;
    
    public void refreshCompleteDataSetIterator()
    {
        completeDataSetIterator = completeDataSets.iterator();
    }
    
    public boolean hasCompleteDataSets()
    {
        return completeDataSets != null && !completeDataSets.isEmpty();
    }

    public boolean hasNextCompleteDataSet()
    {
        if ( completeDataSetIterator == null )
        {
            refreshCompleteDataSetIterator();
        }

        return completeDataSetIterator.hasNext();
    }

    public CompleteDataSet getNextCompleteDataSet()
    {
        if ( completeDataSetIterator == null )
        {
            refreshCompleteDataSetIterator();
        }

        return completeDataSetIterator.next();
    }
    
    public CompleteDataSet getCompleteDataSetInstance()
    {
        return new CompleteDataSet();
    }

    public void close()
    {
    }

    public void closeCompleteDataSet()
    {
    }

    /**
     * Returns the general identifier scheme. IdScheme.NULL is returned if
     * scheme has not been set.
     */
    public IdScheme getIdSchemeProperty()
    {
        String scheme = getIdScheme();
        return IdScheme.from( scheme );
    }

    /**
     * Returns the data element identifier scheme. Falls back to the general
     * identifier scheme if not set. IdScheme.NULL is returned if no scheme has
     * been set.
     */
    public IdScheme getDataElementIdSchemeProperty()
    {
        return getIdScheme( getDataElementIdScheme() );
    }

    /**
     * Returns the organisation unit identifier scheme. Falls back to the general
     * identifier scheme if not set. IdScheme.NULL is returned if no scheme has
     * been set.
     */
    public IdScheme getOrgUnitIdSchemeProperty()
    {
        return getIdScheme( getOrgUnitIdScheme() );
    }

    /**
     * Returns the category option combo identifier scheme. Falls back to the general
     * identifier scheme if not set. IdScheme.NULL is returned if no scheme has
     * been set.
     */
    public IdScheme getCategoryOptionComboIdSchemeProperty()
    {
        return getIdScheme( getCategoryOptionComboIdScheme() );
    }

    /**
     * Returns the data set identifier scheme. Falls back to the general
     * identifier scheme if not set. IdScheme.NULL is returned if no scheme has
     * been set.
     */
    public IdScheme getDataSetIdSchemeProperty()
    {
        return getIdScheme( getDataSetIdScheme() );
    }

    private IdScheme getIdScheme( String objectIdScheme )
    {
        String scheme = getIdScheme();
        scheme = defaultIfEmpty( objectIdScheme, scheme );
        return IdScheme.from( scheme );
    }
    
    //--------------------------------------------------------------------------
    // toString
    //--------------------------------------------------------------------------

    @Override
    public String toString()
    {
        return "[" + completeDataSets.size() + ", " + dataValues.size() + "]";
    }
}
