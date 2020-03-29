import sbt.Keys.{javacOptions, resolvers, scalacOptions, version}
import sbt.{Def, DefaultMavenRepository, Opts, Resolver}

object Common {
  val appVersion = "1.0.0"

  val settings: Seq[Def.Setting[_]] = Seq(
    version := appVersion,
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"), //, "-Xmx2G"),
    scalacOptions ++= Seq("-deprecation", "-unchecked"),
    resolvers += Opts.resolver.mavenLocalFile,
    resolvers ++= Seq(DefaultMavenRepository,
      Resolver.defaultLocal,
      Resolver.mavenLocal
    ))
}