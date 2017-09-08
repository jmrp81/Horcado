<!DOCTYPE helpset PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
"http://java.sun.com/products/javahelp/helpset_1_0.dtd">
<helpset>
	<title>Ayuda del Ahorcado</title>
	<maps>
		<homeID>introduccion</homeID>
		<mapref location="mi_map.jhm"/>
	</maps>
	
	<view>
		<name>Tabla de Contenidos</name>
		<label>Tabla de Contenidos</label>
		<type>javax.help.TOCView</type>
		<data>TOC.xml</data>
	</view>
	
	<view>
		<name>Indice</name>
		<label>El Indice</label>
		<type>javax.help.IndexView</type>
		<data>mi_index.xml</data>
	</view>	

	<view>
		<name>Buscar</name>
		<label>Buscar</label>
		<type>javax.help.SearchView</type>
        <data engine="com.sun.java.help.search.DefaultSearchEngine">
         JavaHelpSearch
        </data>
	</view>	
	
		<!-- Definición de la ventana principal de la ayuda-->  
    <presentation default="true" displayviews="true" displayviewimages="true">  
        <name>MainWin</name>  
            <!-- Dimensiones iniciales -->  
            <size width="640" height="480" /> 
			
            <!-- Posición inicial -->  
            <location x="200" y="200" /> 
			
            <!-- Título de la ventana -->  
            <title>Ayuda del programa </title>  
            <!-- Definimos la barra de herramientas de la ventana -->  
            <toolbar>  
                    <!-- Permitimos ir a la página anterior -->  
                    <helpaction image="BackwardIco">  
                            javax.help.BackAction  
                    </helpaction>  
					
                    <!-- Permitimos ir a la página siguiente -->  
                    <helpaction image="ForwardIco">  
                            javax.help.ForwardAction  
                    </helpaction>  
					
                    <!-- Permitimos imprimir el contenido -->  
                    <helpaction image="PrintIco">  
                            javax.help.PrintAction  
                    </helpaction>  
					
                    <!-- Permitimos configurar la impresión -->  
                    <helpaction image="PrintSetupIco">  
                            javax.help.PrintSetupAction  
                    </helpaction>  
             </toolbar>  
    </presentation>
</helpset>
