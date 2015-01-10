/*
 * generated by Xtext
 */
package org.sqlproc.dsl.generator

import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.sqlproc.dsl.processorDsl.PojoProperty
import org.sqlproc.dsl.ImportManager

import static org.sqlproc.dsl.util.Utils.*
import org.sqlproc.dsl.processorDsl.PojoType
import org.sqlproc.dsl.processorDsl.EnumProperty
import org.sqlproc.dsl.processorDsl.PojoEntity
import com.google.inject.Inject
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveDiscriminator
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectivePrimaryKey
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveRequired
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIndex
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveVersion
import org.sqlproc.dsl.processorDsl.EnumEntity
import java.util.List
import org.sqlproc.dsl.processorDsl.PojoDirectiveOperators
import org.sqlproc.dsl.processorDsl.PojoDao

class ProcessorGeneratorUtils {

	@Inject extension IQualifiedNameProvider

	def compileType(EnumProperty f, ImportManager im) '''
	«IF f.getNative != null»«f.getNative.substring(1)»«ELSEIF f.getType != null»«im.serialize(f.getType)»«ENDIF»'''

	def compileType(PojoProperty f, ImportManager im) '''
	«IF f.getNative != null»«f.getNative.substring(1)»«ELSEIF f.getRef != null»«f.getRef.fullyQualifiedName»«ELSEIF f.
		getType != null»«im.serialize(f.getType)»«ENDIF»«IF f.getGtype != null»<«im.serialize(f.getGtype)»>«ENDIF»«IF f.
		getGref != null»<«f.getGref.fullyQualifiedName»>«ENDIF»«IF f.array»[]«ENDIF»'''

	def compileType(PojoType f, ImportManager im) '''
	«IF f.getNative != null»«f.getNative.substring(1)»«ELSEIF f.getRef != null»«im.serialize(pojoMethod2jvmType(f.getRef))»«ELSEIF f.
		getType != null»«im.serialize(f.getType)»«ENDIF»«IF f.getGtype != null»<«im.serialize(f.getGtype)»>«ENDIF»«IF f.
		getGref != null»<«im.serialize(pojoMethod2jvmType(f.getGref))»>«ENDIF»«IF f.array»[]«ENDIF»'''

	def completeName(PojoEntity e) {
		return getPackage(e) + "." + e.name
	}

	// PojoProperty
	def isRequired(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveRequired]
		return if(d != null) true else false
	}

	def getIndex(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveIndex] as PojoPropertyDirectiveIndex
		return d?.index
	}

	def getUpdateColumn1(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveUpdateCol] as PojoPropertyDirectiveUpdateCol
		return d?.updateColumn1
	}

	def getUpdateColumn2(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveUpdateCol] as PojoPropertyDirectiveUpdateCol
		return d?.updateColumn2
	}

	def getCreateColumn1(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveCreateCol] as PojoPropertyDirectiveCreateCol
		return d?.createColumn1
	}

	def getCreateColumn2(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveCreateCol] as PojoPropertyDirectiveCreateCol
		return d?.createColumn2
	}

	def isDiscriminator(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveDiscriminator]
		return if(d != null) true else false
	}

	def isPrimaryKey(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectivePrimaryKey]
		return if(d != null) true else false
	}

	def isOptLock(PojoProperty f) {
		val d = f.directives?.findFirst[x|x instanceof PojoPropertyDirectiveVersion]
		return if(d != null) true else false
	}

	def isAttribute(PojoProperty f) {
		return if(f.attrs == null || f.attrs.isEmpty()) true else false
	}

	def isList(PojoProperty f) {
		val name = f?.type?.simpleName
		return "List" == name
	}

	// PojoEntity

    def isAbstract(PojoEntity e) {
		val m = e.modifiers1?.findFirst[x|x.abstract]
		return if(m != null) true else false
    }

    def hasOperators(PojoEntity e) {
		val d = e.directives?.findFirst[x|x instanceof PojoDirectiveOperators]
		return if(d != null) true else false
    }

    def getOperatorsSuffix(PojoEntity e) {
		val d = e.directives?.findFirst[x|x instanceof PojoDirectiveOperators] as PojoDirectiveOperators
		return d?.operatorsSuffix
    }

    def isFinal(PojoEntity e) {
		val m = e.modifiers1?.findFirst[x|x.final]
		return if(m != null) true else false
    }

	def PojoEntity getSuperType(PojoEntity e) {
		val m = e.modifiers2?.findFirst[x|x.superType != null]
		return m?.superType
	}

    def PojoEntity getParent(PojoEntity e) {
        if (getDiscriminator(e) != null)
            return null;
        return e.superType
    }

	def PojoProperty getOptLock(PojoEntity e) {
		val fea = e?.features?.findFirst[x|isOptLock(x.feature)]?.feature
		return fea ?: e?.getSuperType?.getOptLock
	}

	def List<PojoProperty> requiredFeatures(PojoEntity e) {
		if (e == null)
			return newArrayList()
		val features = e.features.filter[x|x.feature.isRequired].map[feature].toList
		val se = e.superType
		if (se == null)
			return features
		features.addAll(se.requiredFeatures)
		return features
	}

	def List<PojoProperty> attributes(PojoEntity e) {
		if (e == null)
			return newArrayList()
		val features = e.features.filter[x|x.feature.isAttribute].map[feature].toList
		val se = e.superType
		if (se == null)
			return features
		features.addAll(se.requiredFeatures)
		return features
	}

    def PojoProperty getAttribute(PojoEntity e, String name) {
		if (e == null)
			return null
		val feature = e.features.findFirst[x|x.feature.isAttribute && x.feature.name == name].feature
		return feature ?: e.superType?.getAttribute(name)
    }

	// EnumEntity
    def isFinal(EnumEntity e) {
		val m = e.modifiers1?.findFirst[x|x.final]
		return if(m != null) true else false
    }

	def PojoEntity getSuperType(EnumEntity e) {
		val m = e.modifiers2?.findFirst[x|x.superType != null]
		return m?.superType
	}

	// PojoDao
    def isFinal(PojoDao d) {
		val m = d.modifiers1?.findFirst[x|x.final]
		return if(m != null) true else false
    }

    def isAbstract(PojoDao d) {
		val m = d.modifiers1?.findFirst[x|x.abstract]
		return if(m != null) true else false
    }
}
