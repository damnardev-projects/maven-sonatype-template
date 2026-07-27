# Maven Sonatype Template

A ready-to-use Maven boilerplate for Java 21 multi-module projects, designed for publishing artifacts to Sonatype
repositories.

## Generating a GPG Key

In order to publish artifacts, they must be signed: https://central.sonatype.org/publish/requirements/gpg/. To generate
your GPG key, use the following commands:

1. `gpg --full-generate-key`
2. `gpg --list-signatures` to retrieve `<KEY_ID>` corresponding to the generated key
3. `gpg --keyserver keyserver.ubuntu.com --send-keys <KEY_ID>`

## Creating a Sonatype Account

You can then visit the website: https://central.sonatype.com/. Once on the site, click **"Sign Up"** and fill in the
required information to create your account. If you use your GitHub account to sign in, a namespace will automatically
be created for you.

## Creating a Sonatype Namespace

To create a namespace, sign in to your Sonatype account and open the following
link: https://central.sonatype.com/publishing/namespaces

1. Click **"Register new namespace"**.
2. Enter your namespace `com.example` if your domain name is `example.com`.
3. You must validate the namespace by proving that you own it:
    1. Click the **"Verify Namespace"** button.
    2. A pop-up will open inviting you to add a TXT record to your DNS.
    3. After updating the DNS, click **"Confirm"** to start the namespace validation. This validation may take a few
       minutes.

## Retrieving the Sonatype Token

To retrieve your Sonatype token, go to the page: https://central.sonatype.com/usertoken and click **"Generate User
Token"**, then fill in the requested information. Once the information is validated, a new window will open displaying
the `server` section to copy into your `~/.m2/settings.xml` file along with your Sonatype token.

## Maven Configuration

You must edit the `~/.m2/settings.xml` file to add your Sonatype account information as well as your GPG key details.

In the `servers` section, add the information obtained when generating the Sonatype token:

```xml

<servers>
	<server>
		<id>central</id>
		<username>YOUR_SONATYPE_USERNAME</username>
		<password>YOUR_SONATYPE_PASSWORD</password>
	</server>
</servers>
```

In the `profiles` section, add your GPG key information:

```xml

<profile>
	<id>gpg</id>
	<properties>
		<gpg.keyname>YOUR_GPG_KEY_ID</gpg.keyname>
	</properties>
</profile>
```

and create an environment variable `MAVEN_GPG_PASSPHRASE` with the passphrase of your GPG key.

## Project Configuration

In your project, you must use several Maven plugins to publish artifacts to Sonatype.

| Plugin                                                    | Description                                          |
|-----------------------------------------------------------|------------------------------------------------------|
| `maven-source-plugin`                                     | Generates the sources of your project.               |
| `maven-javadoc-plugin`                                    | Generates the Javadoc documentation of your project. |
| `maven-gpg-plugin`                                        | Signs the artifacts with your GPG key.               |
| `maven-deploy-plugin` + `central-publishing-maven-plugin` | Deploys the artifacts to the Sonatype repository.    |

## Testing Locally

To test the publication of your artifacts locally, use the following command:
`mvn clean deploy -DskipTests -Psonatype,gpg`. This command will:

1. Compile your project
2. Generate the sources and Javadoc documentation
3. Sign the artifacts with your GPG key
4. Deploy the artifacts to your local repository
5. Deploy the artifacts to the Sonatype repository

You must specify the `sonatype` and `gpg` profiles to activate the publishing and signing plugins (to use the correct
GPG key).

In case of an error, check the Maven logs to identify the cause of the problem. If the issue comes from publishing to
Sonatype, you can also visit the page: https://central.sonatype.com/publishing/deployments for additional information (
for example: SNAPSHOT not allowed, missing URL, or missing description).

# GitHub Actions

To publish artifacts to Sonatype, you can use the `publish` GitHub Action. Before running the action, you must configure
the following secrets in your GitHub repository:

- `SONATYPE_USERNAME`: Your Sonatype username
- `SONATYPE_PASSWORD`: Your Sonatype password
- `GPG_PRIVATE_KEY`: Your GPG private key
- `GPG_PASSPHRASE`: The passphrase for your GPG key

You can also refer to https://github.com/actions/setup-java?tab=readme-ov-file#maven-options / https://github.com/actions/setup-java/blob/main/docs/advanced-usage.md#extra-setup-for-pomxml for more information.
