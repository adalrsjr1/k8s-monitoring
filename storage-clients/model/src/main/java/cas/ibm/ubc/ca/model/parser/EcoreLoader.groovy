package cas.ibm.ubc.ca.model.parser

import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.Node
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.URIConverter
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.XMLResource
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl
import org.eclipse.emf.cdo.common.model.EMFUtil
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl
import org.eclipse.xsd.XSDSchema
import org.eclipse.xsd.ecore.EcoreSchemaBuilder
import org.eclipse.xsd.ecore.MapBuilder
import org.eclipse.xsd.ecore.exporter.XSDExporter;
import org.eclipse.xsd.util.XSDResourceFactoryImpl

class EcoreLoader {

	File load(String path) {
		ClassLoader cl = ClassLoader.getSystemClassLoader()
		URL ecoreURL = cl.getResource(path)
		File ecore = new File(ecoreURL.getFile())
		return ecore
	}

	String typeParser(String eType) {
		String[] tokens = eType.split("#//")
		return tokens[tokens.size()-1]
	}

	public void exportEcoreToXSD(ResourceSet ecoreResources_p, String genpath_p) throws Exception {

		ecoreResources_p.getPackageRegistry().put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

		URI genModelURI = URI.createFileURI(genpath_p);
		GenModel genModel = (GenModel) resourceSet.getResource(genModelURI, true).getContents().get(0);

		MapBuilder mapBuilder = new EcoreSchemaBuilder(genModel.getExtendedMetaData());

		EPackage pack = null;
		URI xsdSchemaURI = null;
		Map map = [:]
		for (Resource res : ecoreResources_p.getResources()) {
			EPackage current = (EPackage) res.getContents().get(0);
			println current
			pack = current;
			xsdSchemaURI = res.getURI().appendFileExtension("xsd"); //$NON-NLS-1$
			XSDSchema xsdSchema = ((EcoreSchemaBuilder) mapBuilder).getSchema(pack);
			Resource xsdResource = resourceSet.createResource(xsdSchemaURI);
			xsdResource.getContents().add(xsdSchema);
			try {
				xsdResource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		EcoreLoader loader = new EcoreLoader()
		File ecore = loader.load("model/model.ecore")
		File genmodel = loader.load("model/model.genmodel")

		ResourceSet rs = new ResourceSetImpl()
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new EcoreResourceFactoryImpl())

		EcoreFactory eFactory = EcoreFactory.eINSTANCE
		EcorePackage ePackage = eFactory.getEcorePackage()

		rs.getPackageRegistry().putAt("http://gfads.cin.ufpe.br/model", ePackage)

		org.eclipse.emf.common.util.URI emfURI= org.eclipse.emf.common.util.URI.createURI("/home/adalrsjr1/Code/ibm-stack/storage-clients/model/src/main/resources/model/model.ecore")
		//		println emfURI
		Resource resource = rs.getResource(emfURI, true)
//		EObject eObject = resource.getContents().get(0)
//		println eObject.eContents()

		
		loader.exportEcoreToXSD(rs, "/home/adalrsjr1/Code/ibm-stack/storage-clients/model/src/main/resources/model/model.genmodel")

		File xsd = loader.load("model/model.ecore.xsd")
		GPathResult rootNode = new XmlSlurper().parse(xsd)

		rootNode.children().each { GPathResult node ->
			println node.attributes()
			node.children().each { GPathResult childNode ->
				println "\t" + childNode.attributes()
			}
		}

	}
}
