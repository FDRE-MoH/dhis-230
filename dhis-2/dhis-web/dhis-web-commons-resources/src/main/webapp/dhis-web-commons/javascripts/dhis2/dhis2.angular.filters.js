'use strict';

/* Filters */

var d2Filters = angular.module('d2Filters', [])

.filter('gridFilter', function($filter, CalendarService){    
    
    return function(data, filters, filterTypes){

        if(!data ){
            return;
        }
        
        if(!filters){
            return data;
        }        
        else{            
            
            var dateFilter = {}, 
                textFilter = {}, 
                numberFilter = {},
                filteredData = data;
            
            for(var key in filters){
                
                if(filterTypes[key] === 'DATE'){
                    if(filters[key].start || filters[key].end){
                        dateFilter[key] = filters[key];
                    }
                }
                else if(filterTypes[key] === 'NUMBER' || 
                			filterTypes[key] === 'INTEGER' ||
                			filterTypes[key] === 'INTEGER_POSITIVE' || 
                			filterTypes[key] === 'INTEGER_NEGATIVE' || 
                			filterTypes[key] === 'INTEGER_ZERO_OR_POSITIVE'){
                    if(filters[key].start || filters[key].end){
                        numberFilter[key] = filters[key];
                    }
                }
                else{
                    textFilter[key] = filters[key];
                }
            }
            
            filteredData = $filter('filter')(filteredData, textFilter); 
            filteredData = $filter('filter')(filteredData, dateFilter, dateComparator);            
            filteredData = $filter('filter')(filteredData, numberFilter, numberComparator);
                        
            return filteredData;
        } 
    }; 
    
    function dateComparator(data,filter){
    	var calendarSetting = CalendarService.getSetting(); 
        var start = moment(filter.start, calendarSetting.momentFormat);
        var end = moment(filter.end, calendarSetting.momentFormat);  
        var date = moment(data, calendarSetting.momentFormat); 
        
        if(filter.start && filter.end){
            return ( Date.parse(date) <= Date.parse(end) ) && (Date.parse(date) >= Date.parse(start));
        }        
        return ( Date.parse(date) <= Date.parse(end) ) || (Date.parse(date) >= Date.parse(start));
    }
    
    function numberComparator(data,filter){
        var start = filter.start;
        var end = filter.end;
        
        if(filter.start && filter.end){
            return ( data <= end ) && ( data >= start );
        }        
        return ( data <= end ) || ( data >= start );
    }
})

.filter('paginate', function(Paginator) {
    return function(input, rowsPerPage) {
        if (!input) {
            return input;
        }

        if (rowsPerPage) {
            Paginator.rowsPerPage = rowsPerPage;
        }       
        
        Paginator.itemCount = input.length;

        return input.slice(parseInt(Paginator.page * Paginator.rowsPerPage), parseInt((Paginator.page + 1) * Paginator.rowsPerPage + 1) - 1);
    };
})

/* trim away all single and double quotes in the start and end of a string*/
.filter('trimquotes', function() {
    return function(input) {
        if (!input || (typeof input !== 'string' && !(input instanceof String))) {
            return input;
        }
        
        var beingTrimmed = input;
        var trimmingComplete = false;
        //Trim until no more quotes can be removed.
        while(!trimmingComplete) {
            var beforeTrimming = beingTrimmed;
            beingTrimmed = input.replace(/^'/,"").replace(/'$/,"");
            beingTrimmed = beingTrimmed.replace(/^"/,"").replace(/"$/,"");
            
            if(beforeTrimming.length === beingTrimmed.length) {
                trimmingComplete = true;
            }
        }
        
        
        return beingTrimmed;
    };
})

/* filter out confidential attributes from a list */
.filter('nonConfidential', function() {
  return function( items ) {
    var filtered = [];
    angular.forEach(items, function(item) {
      if(!item.confidential) {
        filtered.push(item);
      }
    });
    return filtered;
  };
})

/* trim away the qualifiers before and after a variable name */
.filter('trimvariablequalifiers', function() {
    return function(input) {
        if (!input || (typeof input !== 'string' && !(input instanceof String))) {
            return input;
        }
        
        var trimmed = input.replace(/^[#VCAvca]{/,"").replace(/}$/,"");
        
        return trimmed;
    };
})

.filter('forLoop', function() {
    return function(input, start, end) {
        input = new Array(end - start);
        for (var i = 0; start < end; start++, i++) {
            input[i] = start;
        }
        return input;
    };
})

.filter('toCalendarDate', function($filter, CalendarService){
    
    return function(input) {
        
        if( input ){            
            var calendarSetting = CalendarService.getSetting();
            
            var year = parseInt($filter('date')(input, 'yyyy'));
            var month = parseInt($filter('date')(input, 'M'));
            var day = parseInt($filter('date')(input, 'd'));
            
            var calendar = $.calendars.instance(calendarSetting.keyCalendar);
            var gregorianDate = $.calendars.instance('gregorian').toJD(year,month,day);            
            var calendarDate = calendar.fromJD(gregorianDate);
            
            return calendar.formatDate('M d, yyyy', calendarDate);
            //return $filter('date')(input, format);
        }
        
        return input;
    };            
})

.filter('categoryOptionFilter', function(){
    
    return function( cos, ou ){
        
        var _cos = [];
        
        if( cos && ou && ou.id ){
            
            angular.forEach(cos, function(co){                
                if( co.mappedOrganisationUnits && co.mappedOrganisationUnits.length > 0 ){                    
                    if( co.mappedOrganisationUnits.indexOf( ou.id ) !== -1){
                        _cos.push( co );
                    }
                }
                else{
                    _cos.push( co );
                }
            });          
        }
        else{
            _cos = cos;
        }
        
        return _cos;
    };
});