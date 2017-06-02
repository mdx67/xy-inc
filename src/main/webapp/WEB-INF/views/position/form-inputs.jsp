<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  	        <div class="form-group">
          <label for="name">name</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><form:input path='name' type='text'/>
<form:errors path='name'/>

          </div>
        </div>
        <div class="form-group">
          <label for="latitude">latitude</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><form:input path='latitude' type='text'/>
<form:errors path='latitude'/>

          </div>
        </div>
        <div class="form-group">
          <label for="longitude">longitude</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><form:input path='longitude' type='text'/>
<form:errors path='longitude'/>

          </div>
        </div>